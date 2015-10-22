(ns now.views
  (:require [re-frame.core :as re-frame]
            [reagent.core :as r :refer [atom]]))

(enable-console-print!)

(def menu-style
  {:list-style :none
   :margin-top 30
   :overflow :hidden})

(def menu-elm-style
  {:height 35
   :width 140
   :line-height "35px"
   :border-radius 3
   :color :#fff
   :cursor :pointer
   :background :#333
   :padding-top 0
   :font-size 17
   :float :left
   :outline :none
   :border :none
   :font-family :sans-serif
   :text-transform :capitalize
   :font-variant :small-caps
   :padding-left 5
   :padding-right 5
   :text-align :center
   :margin-right 5})

(defn val-from-e-target [e]
  (.-value (.-target e)))

;; --------------------
(defn do-it-component []
  (let [show (re-frame/subscribe [:add-new-thing])
        content (atom "")]
    (fn []
      [:div
       (when @show [:form
                    {:on-submit (fn [e]
                                 (.preventDefault e)
                                 (re-frame.core/dispatch [:task-for-now @content]))
                     :style {
                             :margin-top 20
                             }}
                    [:input {:name "chuj"
                             :autofocus true
                             :style {
                                     :height 30
                                     :outline :none
                                     :border 0
                                     :border-bottom "1px solid black"
                                     :padding-left 4
                                     :padding-right 4
                                     }
                             :on-change #(reset! content (val-from-e-target %))
                             :value @content
                             ;; :rows 10
                             ;; :col 40
                             }]])])))

(defn now-component []
  (let [name (re-frame/subscribe [:name])
        ]
    (fn []
      [:div
       [:div {:style menu-style}
        [:button {:style menu-elm-style
             :tabIndex 1
             :on-click (fn []
                         (re-frame/dispatch [:add-new-thing]))}
         "task for now!"]
        [:button {:style menu-elm-style
             :tabIndex 1
             :on-click (fn []
                         (re-frame/dispatch [:mark-current-as-done]))}
         "mark as done"]]
       [do-it-component]])))

(defn later-component []
  (fn []
    [:ul {:style menu-style}
     [:li {:style menu-elm-style} "later"]
     [:li {:style menu-elm-style} "probably not now"]]))

;; --------------------
(defmulti panels identity)
(defmethod panels :now [] [now-component])
(defmethod panels :later [] [later-component])
(defmethod panels :default [] [:div (str (name :none))])

(def title-style {:display :inline-block
                  :font-size 24
                  :line-height 1.5
                  :margin-top 0 :margin-bottom 0
                  :margin-right 10
                  :padding-top 0 :padding-bottom 0})

(def main-panel-style {:font-family "sans-serif"
                       :color :#333
                       :width "70vw"
                       :margin-left :auto
                       :margin-right :auto
                       :margin-top "3vh"})

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])
        current-task (re-frame/subscribe [:current-task])
        current-task-done (re-frame/subscribe [:current-task-done])]

    (println @current-task-done)
    (fn []
      [:div
       {:style main-panel-style}
       [:h1
        {:style title-style}
        "do it now " [:span {:style {:color "#990000"}} "!"]]
       [:h2
        {:style title-style}
        "or later " [:span {:style {:color "#990000"}} "?"]]

       [:div
        {:style {:margin-top 20 :margin-bottom 20}}
        [:h3 (str "current task! :: ")
         [:span {:style {:color (if @current-task-done :blue :red)
                         :border-bottom (if @current-task-done
                                          "1px solid #333" :none)}}
          @current-task]]
        ]

       (panels @active-panel)])))

(def mouse-shortcuts
  (memoize (fn [] (do (.bind js/Mousetrap "shift+/"
                             #(re-frame/dispatch [:set-active-panel :later]))

                      (.bind js/Mousetrap "shift+1"
                             #(re-frame/dispatch [:set-active-panel :now]))))))

(mouse-shortcuts)


#_(defn my-memoize [fnc]
  (fn [& args] (let [memoized (get args some-map false)]
                 (if memoized
                   memoized
                   (apply fnc args)))))
