(ns now.views
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [re-frame.core :as re-frame]
            [reagent.core :as r :refer [atom]]
            [cljs.core.async :as async]))

(enable-console-print!)

;; (def shots (async/chan))

;; (go-loop [seconds 1]
;;   (async/<! (async/timeout 300))
;;   (async/>! shots :shot)
;;   (print "shoot!")
;;   (recur (inc seconds)))

;; (go-loop [seconds 1]
;;   (async/<! shots)
;;   (print "shot from first place")
;;   (async/<! (async/timeout 2300))
;;   (recur (inc seconds)))

;; (go-loop [seconds 1]
;;   (async/<! shots)
;;   (print "shot from second place")
;;   (async/<! (async/timeout 2900))
;;   (recur (inc seconds)))

;; (go-loop [seconds 1]
;;   (async/<! shots)
;;   (print "shot from third place")
;;   (async/<! (async/timeout 3200))
;;   (recur (inc seconds)))

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
(defn do-it-input-base
  "Input element for form in do it component"
  [content task-done live-content show-input]
  [:label
   [:p
    {:style {:user-select :none
             :float :left
             :margin-top 6
             :margin-right 5}
     :on-click (fn []
                 (re-frame/dispatch [:add-new-thing]))}
    "now I will"]
   #_(when (not @task-done) [:p
                           {:style
                            {:float :left
                             :margin-top 6}}
                           @content])
   (when (and @show-input @task-done) [:p])

   [:input {:style {:height 30
                    :outline :none
                    :border 0
                    :border-bottom "1px solid black"
                    ;; :background :#fff
                    :font-size 16
                    :color :#333
                    :padding-left 4
                    :padding-right 4}
            :disabled @task-done
            :on-change #(reset! live-content (val-from-e-target %))
            :value @live-content}]

   ])

(def do-it-input do-it-input-base
  #_(with-meta
    do-it-input-base
    {:component-did-mount #(.focus (reagent.core/dom-node %))}))

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
        [do-it-input current-task current-task-done content show]]])))

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
       [:h2
        {:style title-style}
        "or later " [:span {:style {:color "#990000"}} "?"]]

       [:div
        (panels @active-panel)]
       #_[:article
        [:h1
         {:style {:color (if @current-task-done :#AAA :#333)
                  :margin-top 40
                  :font-size 16
                  :font-weight 400
                  :margin-left 64}}
         @current-task]]])))

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


