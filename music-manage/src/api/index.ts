import {deletes, get, getBaseURL, post,put} from './request'

const HttpManager = {
    // 获取图片信息
    attachImageUrl: (url) => `${getBaseURL()}/${url}`,
    // =======================> 管理员 API 完成
    // 是否登录成功
    getLoginStatus: ({username, password}) => post(`admin/login/status`, {username, password}),
    //查看所有申诉
    viewAllAppeals: () => get(`appeals`),
    viewAllComplaints: ()=> get(`complaints`),
    getComplaintsById: (id)=> get(`/complaint/byId?id=${id}`),
    //更新投诉申诉状态
    updateComplaintStatus: (id, {status}) => post(`complaints/update?id=${id}`, {status}),
    updateAppealStatus:(id,{status}) => post(`appeals/update?id=${id}`,{status}),
    //发送处理消息
    sendMessage: ({userId,userType,message,type}) => post(`notifications/add`,{userId,userType,message,type}),
    // =======================> 用户 API 完成
    // 返回所有用户
    getAllUser: () => get(`user`),
    // 返回指定ID的用户
    getUserOfId: (id) => get(`user/detail?id=${id}`),
    // 删除用户
    deleteUser: (id) => get(`user/delete?id=${id}`),
    // =======================> 收藏列表 API 完成
    // 返回的指定用户ID收藏列表
    getCollectionOfUser: (userId) => get(`collection/detail?userId=${userId}`),
    // 删除收藏的歌曲
    deleteCollection: (userId, songId) => deletes(`collection/delete?userId=${userId}&&songId=${songId}`),

    // =======================> 评论列表 API 完成
    // 获得指定歌曲ID的评论列表
    getCommentOfSongId: (songId) => get(`comment/song/detail?songId=${songId}`),
    // 获得指定歌单ID的评论列表
    getCommentOfSongListId: (songListId) => get(`comment/songList/detail?songListId=${songListId}`),
    // 删除评论
    deleteComment: (id) => get(`comment/delete?id=${id}`),

    // =======================> 歌手 API 完成
    // 返回所有歌手
    getAllSinger: () => get(`singer`),
    // 添加歌手
    setSinger: ({name, sex, birth, location, introduction}) => post(`singer/add`, {
        name,
        sex,
        birth,
        location,
        introduction
    }),
    // 更新歌手信息
    updateSingerMsg: ({id, name, sex, birth, location, introduction}) => post(`singer/update`, {
        id,
        name,
        sex,
        birth,
        location,
        introduction
    }),
    // 删除歌手
    deleteSinger: (id) => deletes(`singer/delete?id=${id}`),

    // =======================> 歌曲 API  完成
    // 返回所有歌曲
    getAllSong: () => get(`song`),
    // 返回指定歌手ID的歌曲
    getSongOfSingerId: (id) => get(`song/singer/detail?singerId=${id}`),
    // 返回的指定用户ID收藏列表
    getSongOfId: (id) => get(`song/detail?id=${id}`),
    // 返回指定歌手名的歌曲
    getSongOfSingerName: (id) => get(`song/singerName/detail?name=${id}`),
    // 更新歌曲信息
    updateSongMsg: ({id, singerId, name, introduction, lyric}) => post(`song/update`, {
        id,
        singerId,
        name,
        introduction,
        lyric
    }),
    updateSongUrl: (id) => `${getBaseURL()}/song/url/update?id=${id}`,
    updateSongImg: (id) => `${getBaseURL()}/song/img/update?id=${id}`,
    updateSongLrc: (id) => `${getBaseURL()}/song/lrc/update?id=${id}`,
    // 删除歌曲
    deleteSong: (id) => deletes(`song/delete?id=${id}`),
    deleteSongByManager: (id, complainterId) => get(`/song/deleteByManager?id=${id}&&complainterId=${complainterId}`),
    undeleteSongByManager: (id, complainterId, appealerId) => get(`/song/undeleteByManager?id=${id}&&complainterId=${complainterId}&&appealerId=${appealerId}`),
    // =======================> 歌单 API 完成
    // 添加歌单
    setSongList: ({title, introduction, style}) => post(`songList/add`, {title, introduction, style}),
    // 获取全部歌单
    getSongList: () => get(`songList`),
    deleteSongList: (id) => get(`songList/delete?id=${id}`),
    // 更新歌单信息
    updateSongListMsg: ({id, title, introduction, style}) => post(`songList/update`, {id, title, introduction, style}),
    //获取歌单byId
    songListConsumerOfId: (id) => get(`/songListConsumer/byId/detail?id=${id}`),
    // 删除歌单
    deleteSongListConsumerByManager: (id, complainterId) => get(`/songListConsumer/deleteByManager?id=${id}&&complainterId=${complainterId}`),
    undeleteSongListConsumerByManager: (id, complainterId, applealerId) => get(`/songListConsumer/undeleteByManager?id=${id}&&complainterId=${complainterId}&&applealerId=${applealerId}`),

    searchBySongListId: (SongListId) => get(`songListConsumer/byId/detail?id=${SongListId}`),

    // =======================> 歌单歌曲 API 完成
    // 给歌单添加歌曲
    setListSong: ({songId,songListId}) => post(`listSong/add`, {songId,songListId}),
    // 返回歌单里指定歌单ID的歌曲
    getListSongOfSongId: (songListId) => get(`listSong/detail?songListId=${songListId}`),
    // 删除歌单里的歌曲
    deleteListSong: (songId) => get(`listSong/delete?songId=${songId}`)

}

export {HttpManager}
