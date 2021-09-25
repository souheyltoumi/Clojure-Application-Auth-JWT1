
(ns application.routes
  (:use     [application.Managers.loginManager :only    [login]]
            [application.Managers.registerManager :only [register]])
  (:require             [application.auth :refer :all]
                        [compojure.core   :refer :all]
                        [compojure.route :as route]))

(defroutes app-routes
  (POST "/register" [] register)
  (GET "/sign" [] sign_token)
  (GET "/login" [] login)
  (GET "/unsign" [] unsign)
  (route/not-found "Error, page not found!"))