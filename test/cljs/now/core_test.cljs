(ns now.core-test
  (:require
   [cljs.test :refer-macros [deftest testing is]]
   [now.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))

(deftest new-test
  (testing "fake description"
    (is (= 2 2))))

