<script>
import axios from 'axios'

export default {
  setup() {
    const pageData = {items: [{}]}
    const doList = (req) => {
      axios({method: 'post', url: `/pb/os/List`, data: req}).then(res => {
        this.items = res.data
      })
    }
    const doList2 = (req) => {
      axios.post(`/pb/os/List`, req).then(res => {
        this.items = res.data
      })
    }
    const doOrder = (req) => {
      axios.post(`/pb/os/Order`, req).then(res => {
        console.log(res)
      })
    }
    return {pageData};
  }
}
</script>
<template>
  <div class="container">
    <ul>
      <li v-for="(item, j) in pageData.items" :key="j">
        <img :src="i.imgPath"/>
        <span class="name">{{ i.name }}</span>
        <span class="price">{{ lib.getNumberFormatted(i.price - i.price * i.discountPer / 100) }}원</span>
        <i class="fa fa-trash" @click="remove(i.id)"></i>
      </li>
    </ul>
    <router-link class="btn btn-primary" to="/order">구입하기</router-link>
  </div>
</template>
