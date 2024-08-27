<template>
  <div class="container">
    <el-table height="550px" border size="small" :data="data">
      <el-table-column label="ID" prop="id" width="50" align="center"></el-table-column>
      <el-table-column label="申诉用户">
        <template v-slot="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column label="类型" prop="targetType" ></el-table-column>
      <el-table-column label="对象id">
        <template v-slot="scope">
          {{ scope.row.targetId }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="160" align="center">
        <template v-slot="scope">
          <el-button type="success" @click="undeleteRow(scope.row.complaintId, scope.row.userId, scope.row.targetType)">恢复</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-paginations
        class="pagination"
        background
        layout="total, prev, pager, next"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="tableData.length"
        @current-change="handleCurrentChange"
    >
    </el-paginations>
  </div>

</template>

<script lang="ts">
import {computed, defineComponent, getCurrentInstance, reactive, ref, watch} from "vue";
import {HttpManager} from "@/api/index";
export default defineComponent({
  setup() {
    const tableData = ref([]); // 记录申诉对象，用于显示
    const pageSize = ref(5); // 页数
    const currentPage = ref(1); // 当前页
    const delVisible = ref(false); // 显示删除框
    const { proxy } = getCurrentInstance(); // 获取组件实例

    // 计算当前表格中的数据
    const data = computed(() => {
      return tableData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
    });

    getData();
    // 获取信息
    async function getData() {
      tableData.value = [];
      try {
        const result = (await HttpManager.viewAllAppeals()) as ResponseBody;
        const appeals = result.data;

        for (const appeal of appeals) {
          try {
            const complaintResult = await HttpManager.getComplaintsById(appeal.complaintId) as ResponseBody;
            if (complaintResult.data && complaintResult.data.length > 0) {
              const complaint = complaintResult.data[0];
              const targetType = complaint.targetType;
              const targetId = complaint.targetId;

              const userResult = await HttpManager.getUserOfId(appeal.userId) as ResponseBody;

              if (userResult.data && userResult.data.length > 0) {
                const user = userResult.data[0]; // 获取user数组中的第一个元素
                const username = user.username;

                // 创建新的表格数据项
                const tableItem = {
                  id: appeal.id,
                  userId: appeal.userId,
                  targetType: targetType,
                  targetId: targetId,
                  complaintId: appeal.complaintId,
                  username: username,
                };


                // 添加到tableData中
                tableData.value.push(tableItem);
              } else {
                console.warn("User data not found for userId:", appeal.userId);
              }
            } else {
              console.warn("Complaint data not found for complaintId:", appeal.complaintId);
            }
          } catch (error) {
            console.error("Error fetching user info:", error);
          }
        }
        currentPage.value = 1;
      } catch (error) {
        console.error("Error fetching complaints:", error);
      }
    }


    // 获取当前页
    function handleCurrentChange(val) {
      currentPage.value = val;
    }

    // 删除行

    async function undeleteRow(complaintId: number, userId: number, targetType: string) {
      try {
        const complaintResult = await HttpManager.getComplaintsById(complaintId) as ResponseBody;
        const complaint = complaintResult.data[0];
        const targetId = complaint.targetId;
        let result: ResponseBody;
        if (targetType == "PLAYLIST") {
          result = await HttpManager.undeleteSongListConsumerByManager(targetId, complaintId, userId) as ResponseBody;
        } else if (targetType == "SONG") {
          result = await HttpManager.undeleteSongByManager(targetId, complaintId, userId) as ResponseBody;
        } else {
          console.warn("Unsupported targetType:", targetType);
          return;
        }
        console.log("unDelete result:", result);
        (proxy as any).$message({
          message: result.message,
          type: result.type,
        });
        if (result.success) {
          console.log("unDelete successful, refreshing data...");
          await getData();
        } else {
          console.warn("unDelete failed:", result.message);
        }
      } catch (error) {
        console.error("Error during undelete operation:", error);
      }
    }
    return {
      data,
      tableData,
      delVisible,
      pageSize,
      currentPage,
      handleCurrentChange,
      confirm,
      undeleteRow
    };
  },
});
</script>

<style scoped></style>
