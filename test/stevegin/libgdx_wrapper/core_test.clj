(ns stevegin.libgdx-wrapper.core-test
  (:require [clojure.test :refer :all]
            [stevegin.libgdx-wrapper.core :refer :all])
  (:import (com.badlogic.gdx Gdx)
           (com.badlogic.gdx.graphics GL10 Mesh VertexAttribute)))

;; Triangle game for testing.
;; Doesn't really run any tests...
(def initial-state {})

(defn init
  [state]
  (let [vertices (float-array [-0.5 -0.5 0 0.5 -0.5 0 0 0.5 0])
        triangles (into-array Short/TYPE [0 1 2])
        attrs (into-array VertexAttribute
                          [(VertexAttribute.
                            com.badlogic.gdx.graphics.VertexAttributes$Usage/Position
                            3 "a_position")])]
    {:mesh (doto (Mesh. true 3 3 attrs)
             (.setVertices vertices)
             (.setIndices triangles))}))

(defn render
  [state]
  (doto (Gdx/gl)
    (.glClear GL10/GL_COLOR_BUFFER_BIT))
  (doto (:mesh state)
    (.render GL10/GL_TRIANGLES 0 3)))

(deftest make-window
  (testing "draw a triangle"
    (create-gameview (atom initial-state)
                     init
                     render
                     "test triangle" 480 320)))
