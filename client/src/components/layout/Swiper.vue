<template>
  <swiper
    :slides-per-view="slidesPerView"
    :space-between="spaceBetween"
    @swiper="onSwiper"
  >
  <button type="button" @click="swiperPrevSlide" class="left"><div class="arrow left"></div></button>

      <swiper-slide v-for="(slide, index) in slides" :key="index">
        <component :is="slide.component" v-bind="slide.props"></component>
    </swiper-slide>
    
<button type="button" @click="swiperNextSlide" class="right"> <div class="arrow right"></div></button>
  </swiper>
</template>
<script setup lang="ts">
export type Slide = { component: any, props: any}
defineProps({
  slidesPerView: {
    type: Number,
    default: 4,
  },
  spaceBetween: {
    type: Number,
    default: 50,
  },
  slides: {
    type: Array<Slide>,
    required: true,
    default: () => []
  },
});
import { ref } from 'vue';
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';

// References
const swiperInstance = ref();

// Methods
function onSwiper(swiper) {
    swiperInstance.value = swiper;
}

const swiperNextSlide = () => {
    swiperInstance.value.slideNext();
};

const swiperPrevSlide = () => {
    swiperInstance.value.slidePrev();
};
</script>

<style scoped lang="scss">
.swipper {
  padding: 0 0 1rem !important;
}
  button {
    z-index: 100;
    position: absolute;
    width: 3rem;
    height: 3rem;
    border-radius: 50%;
    padding: .5rem;
    display: flex;
    justify-content: center;
    align-items: center;
    background: #fff;
    box-shadow: 1px 1px 10px #aaa;
    transition: .2s;
    top: 50%;

    &:hover {
      filter: brightness(95%);
    }

    &.left {
      left: 1rem;
    }
    &.right {
      right: 1rem;
    }
  }

  .arrow {
    z-index: 101;
    border: solid black;
    border-width: 0 3px 3px 0;
    display: inline-block;
    padding: 3px;

    &.right {
    transform: rotate(-45deg);
    -webkit-transform: rotate(-45deg);
    }

    &.left {
        transform: rotate(135deg);
        -webkit-transform: rotate(135deg);
      }
  }
</style>

