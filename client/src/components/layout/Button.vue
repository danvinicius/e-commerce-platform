<template>
    <button :style="{background, color, border, padding}">
        {{ text }}
    </button>
</template>

<script setup lang="ts">
defineProps({
    text: String,
    background: String,
    color: String,
    border: String,
    padding: String,
})
</script>

<style scoped lang="scss">
button {
    padding: .5rem 1.5rem;
    border-radius: .5rem;
    transition: .2s;
    font-size: 1rem;
    
    &:hover {
        transform: scale(1.02)
    }
}
</style>