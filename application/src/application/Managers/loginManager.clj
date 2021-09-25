(ns application.Managers.loginManager

  (:require   [clojure.java.jdbc :as sql]
              [application.auth :refer :all]
              [clojure.data.json :as json]))

(def db-spec {:dbtype "postgresql" :host "localhost" :port 5432 :dbname "test_clojuredb" :user "postgres" :password ""})

(defn get-user [user pass]
  (def query (str "select * from users where (username='" user "' or email='" user "') and password='" pass "';"))
  (def password  (get (first (sql/query  db-spec [query])) :password))
  (def username  (get (first (sql/query  db-spec [query])) :username))
  (def email     (get (first (sql/query  db-spec [query])) :email))
  (def userdata {:email email :username username})
  (if (or (empty? password) (empty? username)) (str (json/write-str {:login false}))
      (str (json/write-str {:login true :userdata userdata :token (generate-signature username email)}))))

(defn login [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> (get-user  (get (get req :params) :username) (get (get req :params) :password)))})
