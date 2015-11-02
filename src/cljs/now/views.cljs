(ns now.views
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [re-frame.core :as re-frame]
            [reagent.core :as r :refer [atom]]
            [cljs.core.async :as async]))

(enable-console-print!)

(defn box
  ([size]
   [:div {:style {:width size :height size}}])
  ([width height]
   [:div {:style {:width width :height height}}]))

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
   :font-family "Arial"
   :text-transform :capitalize
   :font-variant :small-caps
   :padding-left 5
   :padding-right 5
   :text-align :center
   :margin-right 5})

(defn val-from-e-target [e]
  (.-value (.-target e)))

;; --------------------

(defn new-task-input-style
  [active]
  {:height 30
   :outline :none
   :border 0
   :border-bottom "1px solid black" #_(if active
                    "none"
                    "1px solid black")
   :background :#fff
   :font-size 20
   :color :#333
   :padding-left 4
   :padding-right 4})

(defn new-task-input
  [live-content content]
  (fn []
    [:input {:style (new-task-input-style (not (empty? @content)))
             :on-change #(reset! live-content (val-from-e-target %))
             :value @live-content}]))

(def new-task-input+focus
  (with-meta
    new-task-input
    {:component-did-mount #(.focus (reagent.core/dom-node %))}))

(defn new-task-input+label
  "Input element for form in do it component"
  [content task-done live-content]
  [:label
   [:p
    {:style {:user-select :none
             :margin-top 4
             :margin-right 5
             :font-size 20}

     :on-click #(re-frame/dispatch [:add-new-thing])}
    "What do you want to do?"]

   [new-task-input+focus live-content content]

   (when @task-done
     [:p
      {:style {:float :right
               :position :relative
               :left -500}}
      "(y)"])])

(def long-box (partial box "100%"))

(defn do-it-component []
  (let [show (re-frame/subscribe [:add-new-thing])
        content (atom "")
        current-task (re-frame/subscribe [:current-task])
        current-task-done (re-frame/subscribe [:current-task-done])]

    (fn []
      [:div
       [:form
        {:on-submit (fn [e]
                      (.preventDefault e)
                      (re-frame.core/dispatch [:task-for-now @content])
                      #_(reset! content ""))
         :style {:margin-top 20}}

        [new-task-input+label
         current-task
         current-task-done
         content]

        (long-box 40)

        [:h1 "current task: "
         (when-let [current-task @current-task]
           [:span (:content current-task)])]

        (long-box 80)

        [:h1 "todo before: "
         (long-box 20)
         [:input {:type :text
                  :placeholder "now"}]
         (long-box 20)
         [:span "list of todos"]
         (long-box 20)
         ]

        (long-box 20)

        ]])))

(defn now-component []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div
       #_[:div {:style menu-style}
        [:button {:style menu-elm-style
                  :tabIndex 1
                  :on-click #(re-frame/dispatch [:add-new-thing])}
         "task for now!"]
        [:button {:style menu-elm-style
                  :tabIndex 1
                  :on-click #(re-frame/dispatch [:mark-current-as-done])}
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

(def main-panel-style {:font-family "Arial"
                       :color :#333
                       :width "70vw"
                       :margin-left :auto
                       :margin-right :auto
                       :margin-top "3vh"})

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])
        current-task (re-frame/subscribe [:current-task])
        current-task-done (re-frame/subscribe [:current-task-done])]
    (fn []
      [:div
       {:style main-panel-style}
       [:h1
        {:style title-style}
        "do it now " [:span {:style {:color "#990000"}} "!"]]
       #_[:h2
        {:style title-style}
        "or later " [:span {:style {:color "#990000"}} "?"]]

       [:div
        (panels @active-panel)]])))

(def mouse-shortcuts
  (memoize (fn [] (do (.bind js/Mousetrap "shift+/"
                             #(re-frame/dispatch [:set-active-panel :later]))

                      (.bind js/Mousetrap "shift+1"
                             #(re-frame/dispatch [:set-active-panel :now]))))))

(mouse-shortcuts)
