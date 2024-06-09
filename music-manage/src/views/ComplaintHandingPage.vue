<template>
  <div class="container">
    <el-table height="550px" border size="small" :data="data">
      <el-table-column label="ID" prop="id" width="50" align="center"></el-table-column>
      <el-table-column label="投诉用户">
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
          <el-button type="danger" @click="deleteRow(scope.row.targetId, scope.row.userId, scope.row.targetType)">删除</el-button>
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
import mixin from "@/mixins/mixin";
import {HttpManager} from "@/api/index";
import {RouterName} from "@/enums";
import tsyDelDialog from "@/components/dialog/tsyDelDialog.vue";
import axios from 'axios';
export default defineComponent({
  setup() {
    const tableData = ref([]); // 记录投诉对象，用于显示
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
        const result = (await HttpManager.viewAllComplaints()) as ResponseBody;
        const complaints = result.data;

        // 输出初始获取的complaints数据
        console.log("Initial complaints data:", complaints);

        for (const complaint of complaints) {
          try {
            const userResult = (await HttpManager.getUserOfId(complaint.userId)) as ResponseBody;
            if (userResult.data && userResult.data.length > 0) {
              const user = userResult.data[0]; // 获取user数组中的第一个元素
              const username = user.username;
              // 检查每个complaint对象的数据
              console.log("Complaint with user info:", complaint);
              // 创建新的表格数据项
              const tableItem = {
                id: complaint.id,
                userId: complaint.userId,
                targetType: complaint.targetType,
                targetId: complaint.targetId,
                username: username,
              };
              // 添加到tableData中
              tableData.value.push(tableItem);
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

    //删除歌单

    async function deleteRow(targetId: number, userId: number, targetType: string) {
      console.log("Deleting row with targetId:", targetId, "and userId:", userId);
      try {
        let result: ResponseBody;
        if (targetType == "PLAYLIST") {
          result = await HttpManager.deleteSongListConsumerByManager(targetId, userId) as ResponseBody;
        } else if (targetType == "SONG") {
          result = await HttpManager.deleteSongByManager(targetId, userId) as ResponseBody;
        } else {
          console.warn("Unsupported targetType:", targetType);
          return;
        }

        console.log("Delete result:", result);
        (proxy as any).$message({
          message: result.message,
          type: result.type,
        });
        if (result.success) {
          console.log("Delete successful, refreshing data...");
          await getData();
        } else {
          console.warn("Delete failed:", result.message);
        }
      } catch (error) {
        console.error("Error during delete operation:", error);
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
      deleteRow
    };
  },
});
</script>

<style scoped></style>
