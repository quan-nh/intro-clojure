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
nil
true
false
1
1.2
"a"
(type "a")
\a
#".+"
(type #".+")
:keyword
;; @@

;; **
;;; ## data structures
;; **

;; @@
; list
'(1 2 3)

; vector
[1 2 3]
[1 "a" [2 3]]

; set
#{1 2 3}

; map
{:a 1 :b 2}
;; @@

;; **
;;; _It is better to have 100 functions operate on one data structure than to have 10 functions operate on 10 data structures._
;;; 
;;; `Alan Perlis' Epigrams on Programming (1982)`
;; **

;; @@
(get {:a 1 :b 2} :a)
({:a 1 :b 2} :a)
(:a {:a 1 :b 2})

(get [1 2 3] 0)

(assoc {:a 1 :b 2} :c 3)

(update {:a 1 :b 2} :a inc)
;; @@

;; **
;;; ## immutable
;; **

;; @@
(def m {:a 1 :b 2})
(assoc m :c 3)
m
;; @@

;; **
;;; ## functions
;; **

;; @@
(defn add [x y]
  (+ x y))
;; @@

;; **
;;; ![map reduce](https://pbs.twimg.com/media/DwhKw_MVsAAsNXs?format=jpg&name=small)
;; **

;; @@
(map inc [1 2 3])
(filter even? [1 2 3])
(reduce + 10 [1 2 3])
;; @@

;; **
;;; ## java interop
;; **

;; @@
(.toUpperCase "fred")
(System/getProperty "java.vm.version")
Math/PI

(.toString (java.util.Date.))
;; @@

;; **
;;; ## http client
;; **

;; @@
(require '[clj-http.client :as http]
         '[clojure.pprint :refer [pprint print-table]])
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(http/get "https://api.github.com/orgs/wizeline"
          {:as :json})
;; @@

;; @@
(def wizers (:body (http/get "https://api.github.com/orgs/wizeline/members"
                              {:as :json})))

(print-table (map #(select-keys % [:id :login :avatar_url]) wizers))
;; @@
;; ->
;;; 
;;; |      :id |         :login |                                           :avatar_url |
;;; |----------+----------------+-------------------------------------------------------|
;;; | 31629910 |       aarregui | https://avatars0.githubusercontent.com/u/31629910?v=4 |
;;; |  2721809 | alan-maldonado |  https://avatars1.githubusercontent.com/u/2721809?v=4 |
;;; |  3466367 |    alexandercg |  https://avatars1.githubusercontent.com/u/3466367?v=4 |
;;; | 20404938 |       alonsogc | https://avatars3.githubusercontent.com/u/20404938?v=4 |
;;; |  4183514 |        Carlows |  https://avatars2.githubusercontent.com/u/4183514?v=4 |
;;; |   247442 |     ederchrono |   https://avatars2.githubusercontent.com/u/247442?v=4 |
;;; | 16281240 | GalaxiasKyklos | https://avatars0.githubusercontent.com/u/16281240?v=4 |
;;; |    30630 |           ixai |    https://avatars3.githubusercontent.com/u/30630?v=4 |
;;; |  3453385 |        jabas06 |  https://avatars0.githubusercontent.com/u/3453385?v=4 |
;;; |  9245962 |      jalmarazg |  https://avatars1.githubusercontent.com/u/9245962?v=4 |
;;; | 15947670 |          jl-ib | https://avatars0.githubusercontent.com/u/15947670?v=4 |
;;; | 20287172 |    jorgetinoco | https://avatars3.githubusercontent.com/u/20287172?v=4 |
;;; |  1304670 |   juanitodread |  https://avatars0.githubusercontent.com/u/1304670?v=4 |
;;; | 41596204 |   kimberlyluna | https://avatars2.githubusercontent.com/u/41596204?v=4 |
;;; | 23623792 |       LeoUrzua | https://avatars2.githubusercontent.com/u/23623792?v=4 |
;;; |  6756995 |         lojals |  https://avatars2.githubusercontent.com/u/6756995?v=4 |
;;; |     4968 |       pablasso |     https://avatars0.githubusercontent.com/u/4968?v=4 |
;;; |  7118094 |       PkBadger |  https://avatars0.githubusercontent.com/u/7118094?v=4 |
;;; |   448652 |    salvadorbfm |   https://avatars1.githubusercontent.com/u/448652?v=4 |
;;; |   780497 |         shnere |   https://avatars1.githubusercontent.com/u/780497?v=4 |
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(require '[gorilla-repl.table :refer [table-view]]
         '[gorilla-repl.html :refer [html-view]]
         '[hiccup.core :refer [html]])
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(->> wizers
     (map (fn [{:keys [id login avatar_url]}]
            [id
             login
             (html-view (html [:img {:src avatar_url
                                     :width 100
                                     :height 100}]))]))
  	 table-view)
;; @@
;; =>
;;; {"type":"list-like","open":"<center><table>","close":"</table></center>","separator":"\n","items":[{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>31629910</span>","value":"31629910"},{"type":"html","content":"<span class='clj-string'>&quot;aarregui&quot;</span>","value":"\"aarregui\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars0.githubusercontent.com/u/31629910?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/31629910?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[31629910 \"aarregui\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/31629910?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>2721809</span>","value":"2721809"},{"type":"html","content":"<span class='clj-string'>&quot;alan-maldonado&quot;</span>","value":"\"alan-maldonado\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars1.githubusercontent.com/u/2721809?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/2721809?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[2721809 \"alan-maldonado\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/2721809?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>3466367</span>","value":"3466367"},{"type":"html","content":"<span class='clj-string'>&quot;alexandercg&quot;</span>","value":"\"alexandercg\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars1.githubusercontent.com/u/3466367?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/3466367?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[3466367 \"alexandercg\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/3466367?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>20404938</span>","value":"20404938"},{"type":"html","content":"<span class='clj-string'>&quot;alonsogc&quot;</span>","value":"\"alonsogc\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars3.githubusercontent.com/u/20404938?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars3.githubusercontent.com/u/20404938?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[20404938 \"alonsogc\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars3.githubusercontent.com/u/20404938?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>4183514</span>","value":"4183514"},{"type":"html","content":"<span class='clj-string'>&quot;Carlows&quot;</span>","value":"\"Carlows\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars2.githubusercontent.com/u/4183514?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/4183514?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[4183514 \"Carlows\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/4183514?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>247442</span>","value":"247442"},{"type":"html","content":"<span class='clj-string'>&quot;ederchrono&quot;</span>","value":"\"ederchrono\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars2.githubusercontent.com/u/247442?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/247442?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[247442 \"ederchrono\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/247442?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>16281240</span>","value":"16281240"},{"type":"html","content":"<span class='clj-string'>&quot;GalaxiasKyklos&quot;</span>","value":"\"GalaxiasKyklos\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars0.githubusercontent.com/u/16281240?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/16281240?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[16281240 \"GalaxiasKyklos\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/16281240?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>30630</span>","value":"30630"},{"type":"html","content":"<span class='clj-string'>&quot;ixai&quot;</span>","value":"\"ixai\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars3.githubusercontent.com/u/30630?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars3.githubusercontent.com/u/30630?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[30630 \"ixai\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars3.githubusercontent.com/u/30630?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>3453385</span>","value":"3453385"},{"type":"html","content":"<span class='clj-string'>&quot;jabas06&quot;</span>","value":"\"jabas06\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars0.githubusercontent.com/u/3453385?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/3453385?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[3453385 \"jabas06\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/3453385?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>9245962</span>","value":"9245962"},{"type":"html","content":"<span class='clj-string'>&quot;jalmarazg&quot;</span>","value":"\"jalmarazg\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars1.githubusercontent.com/u/9245962?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/9245962?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[9245962 \"jalmarazg\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/9245962?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>15947670</span>","value":"15947670"},{"type":"html","content":"<span class='clj-string'>&quot;jl-ib&quot;</span>","value":"\"jl-ib\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars0.githubusercontent.com/u/15947670?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/15947670?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[15947670 \"jl-ib\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/15947670?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>20287172</span>","value":"20287172"},{"type":"html","content":"<span class='clj-string'>&quot;jorgetinoco&quot;</span>","value":"\"jorgetinoco\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars3.githubusercontent.com/u/20287172?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars3.githubusercontent.com/u/20287172?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[20287172 \"jorgetinoco\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars3.githubusercontent.com/u/20287172?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>1304670</span>","value":"1304670"},{"type":"html","content":"<span class='clj-string'>&quot;juanitodread&quot;</span>","value":"\"juanitodread\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars0.githubusercontent.com/u/1304670?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/1304670?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[1304670 \"juanitodread\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/1304670?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>41596204</span>","value":"41596204"},{"type":"html","content":"<span class='clj-string'>&quot;kimberlyluna&quot;</span>","value":"\"kimberlyluna\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars2.githubusercontent.com/u/41596204?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/41596204?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[41596204 \"kimberlyluna\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/41596204?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>23623792</span>","value":"23623792"},{"type":"html","content":"<span class='clj-string'>&quot;LeoUrzua&quot;</span>","value":"\"LeoUrzua\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars2.githubusercontent.com/u/23623792?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/23623792?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[23623792 \"LeoUrzua\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/23623792?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>6756995</span>","value":"6756995"},{"type":"html","content":"<span class='clj-string'>&quot;lojals&quot;</span>","value":"\"lojals\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars2.githubusercontent.com/u/6756995?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/6756995?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[6756995 \"lojals\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/6756995?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>4968</span>","value":"4968"},{"type":"html","content":"<span class='clj-string'>&quot;pablasso&quot;</span>","value":"\"pablasso\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars0.githubusercontent.com/u/4968?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/4968?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[4968 \"pablasso\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/4968?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>7118094</span>","value":"7118094"},{"type":"html","content":"<span class='clj-string'>&quot;PkBadger&quot;</span>","value":"\"PkBadger\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars0.githubusercontent.com/u/7118094?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/7118094?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[7118094 \"PkBadger\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/7118094?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>448652</span>","value":"448652"},{"type":"html","content":"<span class='clj-string'>&quot;salvadorbfm&quot;</span>","value":"\"salvadorbfm\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars1.githubusercontent.com/u/448652?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/448652?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[448652 \"salvadorbfm\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/448652?v=4\\\" width=\\\"100\\\" />\"}]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-unkown'>780497</span>","value":"780497"},{"type":"html","content":"<span class='clj-string'>&quot;shnere&quot;</span>","value":"\"shnere\""},{"type":"html","content":"<img height=\"100\" src=\"https://avatars1.githubusercontent.com/u/780497?v=4\" width=\"100\" />","value":"#gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/780497?v=4\\\" width=\\\"100\\\" />\"}"}],"value":"[780497 \"shnere\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/780497?v=4\\\" width=\\\"100\\\" />\"}]"}],"value":"#gorilla_repl.table.TableView{:contents ([31629910 \"aarregui\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/31629910?v=4\\\" width=\\\"100\\\" />\"}] [2721809 \"alan-maldonado\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/2721809?v=4\\\" width=\\\"100\\\" />\"}] [3466367 \"alexandercg\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/3466367?v=4\\\" width=\\\"100\\\" />\"}] [20404938 \"alonsogc\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars3.githubusercontent.com/u/20404938?v=4\\\" width=\\\"100\\\" />\"}] [4183514 \"Carlows\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/4183514?v=4\\\" width=\\\"100\\\" />\"}] [247442 \"ederchrono\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/247442?v=4\\\" width=\\\"100\\\" />\"}] [16281240 \"GalaxiasKyklos\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/16281240?v=4\\\" width=\\\"100\\\" />\"}] [30630 \"ixai\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars3.githubusercontent.com/u/30630?v=4\\\" width=\\\"100\\\" />\"}] [3453385 \"jabas06\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/3453385?v=4\\\" width=\\\"100\\\" />\"}] [9245962 \"jalmarazg\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/9245962?v=4\\\" width=\\\"100\\\" />\"}] [15947670 \"jl-ib\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/15947670?v=4\\\" width=\\\"100\\\" />\"}] [20287172 \"jorgetinoco\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars3.githubusercontent.com/u/20287172?v=4\\\" width=\\\"100\\\" />\"}] [1304670 \"juanitodread\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/1304670?v=4\\\" width=\\\"100\\\" />\"}] [41596204 \"kimberlyluna\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/41596204?v=4\\\" width=\\\"100\\\" />\"}] [23623792 \"LeoUrzua\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/23623792?v=4\\\" width=\\\"100\\\" />\"}] [6756995 \"lojals\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars2.githubusercontent.com/u/6756995?v=4\\\" width=\\\"100\\\" />\"}] [4968 \"pablasso\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/4968?v=4\\\" width=\\\"100\\\" />\"}] [7118094 \"PkBadger\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars0.githubusercontent.com/u/7118094?v=4\\\" width=\\\"100\\\" />\"}] [448652 \"salvadorbfm\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/448652?v=4\\\" width=\\\"100\\\" />\"}] [780497 \"shnere\" #gorilla_repl.html.HtmlView{:content \"<img height=\\\"100\\\" src=\\\"https://avatars1.githubusercontent.com/u/780497?v=4\\\" width=\\\"100\\\" />\"}]), :opts nil}"}
;; <=
