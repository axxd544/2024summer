<template>
    <el-drawer
      title="播放记录"
      v-model="playHistoryVisible"
      direction="btt"
      size="50%"
      :wrapper-closable="false"
      :show-close="true"
    >
      <el-table :data="dataList" style="width: 100%">
        <el-table-column prop="songName" label="歌曲名" width="180"></el-table-column>
        <el-table-column prop="singerName" label="歌手名" width="180"></el-table-column>
        <el-table-column prop="playTimeStamp" label="播放时间" width="180"></el-table-column>
        <el-table-column label="操作" width="120">
          <template v-slot="scope">
            <el-button type="text" @click="playSong(scope.row)">播放</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref, computed, onMounted } from "vue";
  import { useStore } from "vuex";
  import { HttpManager } from "@/api";
  import mixin from "@/mixins/mixin";
  
  export default defineComponent({
    name: "PlayHistory",
    props: {
      visible: {
        type: Boolean,
        required: true,
      },
      'onUpdate:visible': {
        type: Function,
        required: true,
      },
    },
    setup(props) {
      const store = useStore();
      const { playMusic, getSongTitle, getSingerName, checkStatus } = mixin();
  
      const playHistoryVisible = computed({
        get: () => props.visible,
        set: (value) => props['onUpdate:visible'](value),
      });
  
      const playHistory = ref([]);
      const dataList = ref([]);
  
      onMounted(async () => {
        if (checkStatus()) {
          const userId = store.getters.userId;
          const response = await (HttpManager.getPlayHistory(userId)) as ResponseBody;
          const historyData = response.data.data; // 获取data中的data
  
          for (const item of historyData) {
            const songResponse = await (HttpManager.songOfId(item.songId)) as ResponseBody;
            const songData = songResponse.data[0];
            dataList.value.push({
              ...item,
              songName: getSongTitle(songData.name),
              singerName: getSingerName(songData.name) || '未知歌手',
              songPic: songData.pic,
              songUrl: songData.url,
            });
          }
  
          playHistory.value = dataList.value;
        }
      });
  
      const playSong = (song) => {
        playMusic({
          id: song.songId,
          url: song.songUrl,
          pic: song.songPic,
          name: song.songName,
          index: 1,
          lyric: song.lyric,
          currentSongList: playHistory.value,
        });
      };
  
      return {
        playHistoryVisible,
        playHistory,
        dataList,
        playSong,
      };
    },
  });
  </script>
  
  <style scoped>
  .el-drawer {
    .el-drawer__body {
      padding: 0;
    }
  }
  .el-table {
    width: 100%;
  }
  .el-table__cell {
    padding: 10px;
  }
  </style>
  