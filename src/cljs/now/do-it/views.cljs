(ns now.do-it.views
  (:require [re-frame.core :as re-frame]
            [reagent.core :as r :refer [atom]]))

#_(defn do-it-component []
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

        [:h1 "todo before: "]

        (long-box 20)

        ]])))

