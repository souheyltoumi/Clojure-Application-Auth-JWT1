(ns application.auth
  (:require [buddy.sign.jwt :as jwt]))

(defonce secret "86bae26023208e57a5880d5ad644143c567fc57baaf5a942")

(defn generate-signature [user email]
  (jwt/sign {:user email :email email} secret))

(defn unsign-token [token]
  (jwt/unsign token secret))

(defn sign_token [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->>
             (if (or (empty? (get (get req :params) :email)) (empty? (get (get req :params) :password))) "Provide valid Email or password"
                 (generate-signature (get (get req :params) :email) (get (get req :params) :password))))})
(defn unsign [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->>
             (unsign-token (get (get req :params) :token)))})