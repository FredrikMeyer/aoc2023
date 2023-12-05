(ns fredrikmeyer.read 
  (:require
   [clojure.java.io :as io]
   [clojure.string :as s]))

(defn read-day [filename]
  (->> filename
       io/resource
       slurp
       s/trim))
