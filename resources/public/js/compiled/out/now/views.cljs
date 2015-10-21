(ns now.views
  (:require [re-frame.core :as re-frame]))

(enable-console-print!)

(def menu-style
  {:list-style :none
   :margin-top 30
   :overflow :hidden})

(def menu-elm-style
  {:height 70
   :width 70
   :color :#fff
   :background :#333
   :padding-top 16
   :float :left
   :padding-left 5
   :padding-right 5
   :margin-right 5})

;; --------------------
(defn do-it-component []
  (fn []
    [:div
     [:textarea]]))

(defn now-component []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:ul {:style menu-style}
       [:li {:style menu-elm-style} "create it for now!"]
       [:li {:style menu-elm-style} "check everything!"]])))

(defn later-component []
  (fn []
    [:ul {:style menu-style}
     [:li {:style menu-elm-style} "later"]
     [:li {:style menu-elm-style} "probably not now"]]))


;; --------------------
(defmulti panels identity)
(defmethod panels :now [] [now-component])
(defmethod panels :later [] [later-component])
(defmethod panels :default [] [:div])

(def title-style {:style
                  {:display :inline-block
                   :font-size 24
                   :line-height 1.5
                   :margin-top 0 :margin-bottom 0
                   :margin-right 10
                   :padding-top 0 :padding-bottom 0}})

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [:div
       {:style
        {:font-family "sans-serif"
         :color :#333
         :width "70vw"
         :margin-left :auto
         :margin-right :auto
         :margin-top "3vh"}}

       [:h1
        title-style
        "do it now " [:span {:style {:color "#990000"}} "!"]]
       [:h2
        title-style
        "or later " [:span {:style {:color "#990000"}} "?"]]

       (panels @active-panel)])))

(def mouse-shortcuts
  (memoize (fn [] (do (.bind js/Mousetrap "shift+/"
                             #(re-frame/dispatch [:set-active-panel :later]))

                      (.bind js/Mousetrap "shift+1"
                             #(re-frame/dispatch [:set-active-panel :now]))))))

(mouse-shortcuts)

;; document.body.addEventListener('keydown', function (e) {
;;     alert('hello world');
;; });
