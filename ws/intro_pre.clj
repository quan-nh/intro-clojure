;; gorilla-repl.fileformat = 1

;; **
;;; # Intro to Clojure
;;; 
;;; - LISP on JVM
;;; - dynamic language
;;; - functional language
;; **

;; **
;;; ## hello world
;; **

;; @@
(print "Hello, World!")
;; @@

;; **
;;; 
;; **

;; **
;;; ## data types
;; **

;; @@

;; @@

;; @@




















;; @@

;; **
;;; ## data structures
;; **

;; @@

;; @@

;; @@




















;; @@

;; **
;;; _It is better to have 100 functions operate on one data structure than to have 10 functions operate on 10 data structures._
;;; 
;;; `Alan Perlis' Epigrams on Programming (1982)`
;; **

;; @@

;; @@

;; @@




















;; @@

;; **
;;; ## immutable
;; **

;; @@

;; @@

;; @@




















;; @@

;; **
;;; ## functions
;; **

;; @@

;; @@

;; @@




















;; @@

;; **
;;; [map reduce](https://pbs.twimg.com/media/DwhKw_MVsAAsNXs?format=jpg&name=small)
;; **

;; @@

;; @@

;; @@




















;; @@

;; **
;;; ## java interop
;; **

;; @@

;; @@

;; @@




















;; @@

;; **
;;; ## http client
;; **

;; @@
(require '[clj-http.client :as http])
;; @@

;; @@

;; @@

;; @@

;; @@

;; @@

;; @@

;; @@




















;; @@

;; @@
(require '[gorilla-repl.table :refer [table-view]]
         '[gorilla-repl.html :refer [html-view]])

(require '[hiccup.core :refer [html]])
;; @@

;; @@

;; @@

;; @@

;; @@
