<template>
  <div>
    <span @click="videoClick()"> 点击播放</span>
   <span @click="videoPause()"> 点击暂停</span>
    <video width="1000" controls="controls" ref="video">
        <source src="../assets/video/1.mp4" type="video/mp4"/>
        <object data="../assets/video/1.mp4" width="1000" height="1000">
            你的浏览器不支持该格式
        </object>
    </video>
  </div>
</template>

<script >
export default {
    data() {
      return {
      }
    },
    methods:{
        videoClick(){
            this.$refs.video.play();
            this.isPlay = true
        },
        videoPause(){
            this.$refs.video.pause();
            this.isPlay = false
        }
    }
}
</script>
<style>

</style>