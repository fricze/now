(ns now.views
  (:require [re-frame.core :as re-frame]
            [reagent.core :as r]))

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

(defn val-from-e-target [e]
  (.-value (.-target e)))

;; --------------------
(defn do-it-component []
  (let [show (re-frame/subscribe [:add-new-thing])
        content (r/atom "")]
    (fn []
      [:div
       [:h1 "area"]
       (when @show [:textarea {:name "chuj"
                               :on-change #(reset! content (val-from-e-target %))
                               :value @content
                               :rows 10
                               :col 40}])])))

(defn now-component []
  (let [name (re-frame/subscribe [:name])
        ]
    (fn []
      [:div
       [:ul {:style menu-style}
        [:li {:style menu-elm-style
              :on-click (fn []
                          (re-frame/dispatch [:add-new-thing]))}
         "create it for now!"]
        [:li {:style menu-elm-style} "check everything!"]]
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
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [:div
       {:style main-panel-style}
       [:h1
        {:style title-style}
        "do it now " [:span {:style {:color "#990000"}} "!"]]
       [:h2
        {:style title-style}
        "or later " [:span {:style {:color "#990000"}} "?"]]

       (panels @active-panel)])))

(def mouse-shortcuts
  (memoize (fn [] (do (.bind js/Mousetrap "shift+/"
                             #(re-frame/dispatch [:set-active-panel :later]))

                      (.bind js/Mousetrap "shift+1"
                             #(re-frame/dispatch [:set-active-panel :now]))))))

(mouse-shortcuts)


(defn my-memoize [fnc]
  (fn [& args] (let [memoized (get args some-map false)]
                 (if memoized
                   memoized
                   (apply fnc args)))))
