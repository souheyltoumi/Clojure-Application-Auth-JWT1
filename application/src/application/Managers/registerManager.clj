(ns application.Managers.registerManager
  (:use [application.Managers.loginManager :only [get-user db-spec]])
  (:require   [clojure.java.jdbc :as sql]
              [application.auth :refer :all]
              [clojure.data.json :as json]))

(defn save-image [image_path user email pass]
  (clojure.java.io/copy
   (clojure.java.io/file image_path)
   (clojure.java.io/file (str "server_images/image_" (generate-signature user email) ".jpeg")))
  (get-user user pass))

(defn add-user-sql [user email pass image_path]
  (def query (str "insert into users (username,password,email) values (" "'" user "'," "'" pass "','" email "')"))
  (println query)
  (sql/execute! db-spec [query]) (save-image image_path  user email pass))

(defn register [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (add-user-sql (get (get req :params) :username) (get (get req :params) :email)   (get (get req :params) :password) (str  (get ((get req :params) :image) :tempfile))))})
