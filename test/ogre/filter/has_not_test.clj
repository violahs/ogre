(ns ogre.filter.has-not-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]
            [ogre.test-util :as u]))

(deftest test-has-not-step
  (g/use-new-tinker-graph!)
  (testing "test_g_V_hasNotXname_markoX"
    (let [vs (q/query (g/get-vertices)
                      (q/has-not :name "marko")                    
                      (q/into-vec!))]
      (is (= 5 (count vs)))
      (is (not (#{"marko"} (u/get-names vs))))))

  (testing "test_g_V_hasNotXname_blahX"
    (let [vs (q/query (g/get-vertices)
                      (q/has-not :name "blah")                    
                      (q/into-vec!))]
      (is (= 6 (count vs)))
      (is (not (#{"blah"} (u/get-names vs))))))
  
  (testing "test_g_V_hasNotXblah_nullX"
    (let [vs (q/query (g/get-vertices)
                      (q/has-not :blah nil)                    
                      (q/into-vec!))]
      (is (= 0 (count vs)))))  
  (testing "test_g_V_hasNotXage_gt_32X"
    (let [vs (q/query (g/get-vertices)
                      (q/has-not :age > (int 32))                    
                      (q/into-vec!))]
      (is (= 3 (count vs)))
      (is (every? (partial >= 32) (u/get-ages vs))))))