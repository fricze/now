(ns now.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))

(re-frame/register-sub
 :add-new-thing
 (fn [db _]
   (reaction (:new-thing @db))))


(re-frame/register-sub
 :current-task
 (fn [db _]
   (reaction (last (:tasks @db)))))

(re-frame/register-sub
 :current-task-done
 (fn [db _]
   (reaction (:current-task-done @db))))
