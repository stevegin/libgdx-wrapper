(defproject libgdx-wrapper "0.1.0-SNAPSHOT"
  :description "clojure libgdx wrapper"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/core.async "0.1.242.0-44b1e3-alpha"]
                 [com.badlogic.gdx/gdx "0.9.9-SNAPSHOT"]
                 [com.badlogic.gdx/gdx-backend-lwjgl "0.9.9-SNAPSHOT"]]
  :repositories [["libgdx" "http://libgdx.badlogicgames.com/nightlies/maven/"]])
