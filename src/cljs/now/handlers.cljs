(ns now.handlers
  (:require [re-frame.core :as re-frame]
            [now.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/register-handler
 :add-new-thing
 (fn [db _]
   (let [new-thing (not (:new-thing db))]
       (assoc db :new-thing new-thing))))

(re-frame/register-handler
 :task-for-now
 (fn [db [_ task]]
   (let [db (assoc db :current-task-done false)
         db (assoc db :current-task task)]
     db)))

(re-frame/register-handler
 :mark-current-as-done
 (fn [db _]
   (assoc db :current-task-done true)))
