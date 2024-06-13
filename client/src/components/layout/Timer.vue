<template>
  <div class="timer flex">
    <div class="days flex column align-center gap-5 relative">
      <p>Days</p>
      <p v-html="formattedDays"></p>
    </div>
    <div class="hours flex column align-center gap-5 relative">
      <p>Hours</p>
      <p v-html="formattedHours"></p>
    </div>
    <div class="minutes flex column align-center gap-5 relative">
      <p>Minutes</p>
      <p v-html="formattedMinutes"></p>
    </div>
    <div class="seconds flex column align-center gap-5">
      <p>Seconds</p>
      <p v-html="formattedSeconds"></p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";

const props = defineProps({
  time: {
    type: Date,
    required: true,
  },
});

const days = ref(0);
const hours = ref(0);
const minutes = ref(0);
const seconds = ref(0);
const formattedDays = ref("");
const formattedHours = ref("");
const formattedMinutes = ref("");
const formattedSeconds = ref("");

const updateTimer = () => {
  const diff = props.time.getTime() - new Date().getTime();
  days.value = diff / 1000 / 60 / 60 / 24;
  hours.value = (days.value - Math.floor(days.value)) * 24;
  minutes.value = (hours.value - Math.floor(hours.value)) * 60;
  seconds.value = (minutes.value - Math.floor(minutes.value)) * 60;

  formattedDays.value =
    Math.floor(days.value) >= 10
      ? Math.floor(days.value).toString()
      : `0${Math.floor(days.value)}`;
  formattedHours.value =
    Math.floor(hours.value) >= 10
      ? Math.floor(hours.value).toString()
      : `0${Math.floor(hours.value)}`;
  formattedMinutes.value =
    Math.floor(minutes.value) >= 10
      ? Math.floor(minutes.value).toString()
      : `0${Math.floor(minutes.value)}`;
  formattedSeconds.value =
    Math.floor(seconds.value) >= 10
      ? Math.floor(seconds.value).toString()
      : `0${Math.floor(seconds.value)}`;
};

onMounted(() => {
  updateTimer();
  setInterval(updateTimer, 1000);
});
</script>

<style scoped lang="scss">
.timer {
  background: #f2f5ff;
  padding: 0.5rem 1rem;
  border-radius: 0.5rem;

  & > div {
    padding: 0.5rem;
    width: 3rem;

    p:first-child {
      font-size: 0.75rem;
    }
    p:nth-child(2) {
      font-size: 1.5rem;
    }
  }

  & > div:not(:last-child) {
    &::after {
      content: "";
      position: absolute;
      right: 0;
      bottom: -0.5rem;
      height: 2.2rem;
      width: 1px;
      background: #d2d2d2;
      transform: translateY(-50%);
    }
  }
}
</style>