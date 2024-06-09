import { createApp } from "vue";
import ElementPlus from "element-plus";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "element-plus/dist/index.css";
import "./assets/css/main.css";
import "./assets/icons/iconfont.js";


import { Store } from "vuex";

// 去抖动函数
const debounce = (fn: (...args: any[]) => void, delay: number) => {
  let timer: number | null = null;
  return (...args: any[]) => {
    if (timer !== null) {
      clearTimeout(timer);
    }
    timer = window.setTimeout(() => {
      fn(...args);
    }, delay);
  };
};

// 包装 ResizeObserver
const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
  constructor(callback: ResizeObserverCallback) {
    super(debounce(callback, 16));
  }
};

declare module "@vue/runtime-core" {
  interface State {
    count: number;
  }

  interface ComponentCustomProperties {
    $store: Store<State>;
  }

  
  
}

createApp(App).use(store).use(router).use(ElementPlus).mount("#app");
