(ns figwheel.connect (:require [now.core] [figwheel.client] [figwheel.client.utils]))
(figwheel.client/start {:websocket-url "ws://localhost:3449/figwheel-ws", :on-jsload (fn [& x] (if js/now.core.mount-root (apply js/now.core.mount-root x) (figwheel.client.utils/log :debug "Figwheel: :on-jsload hook 'now.core/mount-root' is missing"))), :build-id "dev"})

