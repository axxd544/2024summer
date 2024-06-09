<template>
    <div class="complaint-page">
      <el-tabs v-model="activeTab">
        <el-tab-pane label="投诉" name="complaints">
          <el-table :data="complaints" style="width: 100%">
            <el-table-column prop="userId" label="发起人">
              <template #default="scope">
                <span>{{ getUserName(scope.row.userId) }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="target" label="投诉对象">
              <template #default="scope">
                <span>{{ getTargetName(scope.row) }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="reason" label="投诉原因" />
            <el-table-column prop="status" label="处理状态">
              <template #default="scope">
                <el-tag :type="getTagType(scope.row.status)">{{ scope.row.status }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <div v-if="scope.row.status === 'PENDING'">
                  <el-button type="success" @click="handleApprove(scope.row, 'complaint')">同意</el-button>
                  <el-button type="danger" @click="handleReject(scope.row, 'complaint')">驳回</el-button>
                </div>
                <div v-else>
                  <span>🙂</span>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="申诉" name="appeals">
          <el-table :data="appeals" style="width: 100%">
            <el-table-column prop="userId" label="发起人">
              <template #default="scope">
                <span>{{ getUserName(scope.row.userId) }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="target" label="投诉对象">
              <template #default="scope">
                <span>{{ getTargetName(scope.row) }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="reason" label="申诉原因" />
            <el-table-column prop="status" label="处理状态">
              <template #default="scope">
                <el-tag :type="getTagType(scope.row.status)">{{ scope.row.status }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <div v-if="scope.row.status === 'PENDING'">
                  <el-button type="success" @click="handleApprove(scope.row, 'appeal')">同意</el-button>
                  <el-button type="danger" @click="handleReject(scope.row, 'appeal')">驳回</el-button>
                </div>
                <div v-else>
                  <span>🙂</span>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref, onMounted } from "vue";
  import { HttpManager } from "@/api";
  import { ElMessage } from "element-plus";
  
  export default defineComponent({
    setup() {
      const activeTab = ref("complaints");
      const complaints = ref([]);
      const appeals = ref([]);
      const userMap = ref({});
      const targetMap = ref({});
  
      // 获取用户详细信息
      const fetchUserDetails = async (userId) => {
        const result = await (HttpManager.getUserOfId(userId)) as ResponseBody;
        if (result.success) {
          userMap.value[userId] = result.data[0].username; // 确保正确的数据结构
        } else {
          userMap.value[userId] = "未知用户";
        }
      };
  
      // 获取投诉对象信息
      const fetchTargetDetails = async (complaint) => {
        let result;
        if (complaint.targetType === "PLAYLIST") {
          result = await (HttpManager.searchBySongListId(complaint.targetId)) as ResponseBody;
        } else if (complaint.targetType === "SONG") {
          result = await (HttpManager.getSongOfId(complaint.targetId)) as ResponseBody;
        }
        if (result && result.success) {
          targetMap.value[complaint.id] = complaint.targetType === "PLAYLIST" ? `用户歌单——${result.data[0].title}` : `歌曲——${result.data[0].name}`;
        } else {
          targetMap.value[complaint.id] = "未知对象";
        }
      };
  
      // 获取投诉信息
      const fetchComplaints = async () => {
        const result = await (HttpManager.viewAllComplaints()) as ResponseBody;
        if (result.success) {
          complaints.value = result.data;
          await Promise.all(result.data.map(async (complaint) => {
            await fetchUserDetails(complaint.userId);
            await fetchTargetDetails(complaint);
          }));
        } else {
          ElMessage.error("获取投诉信息失败");
        }
      };
  
      // 获取申诉信息
      const fetchAppeals = async () => {
        const result = await (HttpManager.viewAllAppeals()) as ResponseBody;
        if (result.success) {
          appeals.value = result.data;
          await Promise.all(result.data.map(async (appeal) => {
            await fetchUserDetails(appeal.userId);
            await fetchTargetDetails(appeal);
          }));
        } else {
          ElMessage.error("获取申诉信息失败");
        }
      };
  
      // 获取用户名
      const getUserName = (userId) => {
        return userMap.value[userId] || "未知用户";
      };
  
      // 获取投诉对象名称
      const getTargetName = (item) => {
        return targetMap.value[item.id] || "未知对象";
      };
  
      // 获取标签类型
      const getTagType = (status) => {
        switch (status) {
          case "PENDING":
            return "warning";
          case "REVIEWED":
            return "success";
          case "DISMISSED":
            return "danger";
          default:
            return "";
        }
      };
  
      // 处理同意操作
      const handleApprove = async (item, type) => {
        if (type === "complaint") {
          const updateResult = await (HttpManager.updateComplaintStatus(item.id, { status: "REVIEWED" })) as ResponseBody;
          if (updateResult.success) {
            ElMessage.success("投诉处理成功");
            fetchComplaints();
          } else {
            ElMessage.error("更新投诉状态失败");
          }
        } else if (type === "appeal") {
          const appealComplaintId = item.complaintId;
          const updateAppealResult = await (HttpManager.updateAppealStatus(item.id, { status: "REVIEWED" })) as ResponseBody;
          const updateComplaintResult = await (HttpManager.updateComplaintStatus(appealComplaintId, { status: "DISMISSED" })) as ResponseBody;
          if (updateAppealResult.success && updateComplaintResult.success) {
            ElMessage.success("申诉处理成功");
            fetchAppeals();
            fetchComplaints();
          } else {
            ElMessage.error("更新状态失败");
          }
        }
      };
  
      // 处理驳回操作
      const handleReject = async (item, type) => {
        try {
          const rejectReason = prompt("请输入驳回原因：");
          if (!rejectReason) {
            ElMessage.warning("驳回原因不能为空");
            return;
          }
          if (type === "complaint") {
            const sendMessageResult = await (HttpManager.sendMessage({
              userId: item.userId,
              userType: "consumer",
              message: `您的投诉被驳回，原因：${rejectReason}`,
              type: 2
            })) as ResponseBody;
            if (sendMessageResult.success) {
              const updateResult = await (HttpManager.updateComplaintStatus(item.id, { status: "DISMISSED" })) as ResponseBody;
              if (updateResult.success) {
                ElMessage.success("投诉驳回成功");
                fetchComplaints();
              } else {
                ElMessage.error("更新投诉状态失败");
              }
            } else {
              ElMessage.error("发送消息失败");
            }
          } else if (type === "appeal") {
            const appealComplaintId = item.complaintId;
            const updateAppealResult = await (HttpManager.updateAppealStatus(item.id, { status: "DISMISSED" })) as ResponseBody;
            const updateComplaintResult = await (HttpManager.updateComplaintStatus(appealComplaintId, { status: "REVIEWED" })) as ResponseBody;
            if (updateAppealResult.success && updateComplaintResult.success) {
              ElMessage.success("申诉驳回成功");
              fetchAppeals();
              fetchComplaints();
            } else {
              ElMessage.error("更新状态失败");
            }
          }
        } catch (error) {
          console.error("Error handling reject:", error);
          ElMessage.error("操作失败，请检查控制台日志以获取详细信息");
        }
      };
  
      onMounted(() => {
        setTimeout(() => {
          fetchComplaints();
          fetchAppeals();
        }, 100);
      });
  
      return {
        activeTab,
        complaints,
        appeals,
        getUserName,
        getTargetName,
        getTagType,
        handleApprove,
        handleReject,
      };
    },
  });
  </script>
  
  <style scoped>
  .complaint-page {
    padding: 20px;
    overflow: hidden;
  }
  
  * {
    animation: none !important;
    transition: none !important;
  }
  .html {
    overflow: hidden;
  }
  </style>
  