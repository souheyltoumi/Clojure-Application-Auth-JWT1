(ns application.core

  (:require [org.httpkit.server :as server]
            [application.routes :refer :all]
            [application.cors :refer :all]
            [ring.middleware.defaults :refer :all])

  (:gen-class))

(defn -main
  "This is our main entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (server/run-server
     (all-cors (wrap-defaults #'app-routes (assoc site-defaults :security false))) {:port port}) (println "Running on" port) (println (str "Running webserver at http:/127.0.0.1:3000/"))))

