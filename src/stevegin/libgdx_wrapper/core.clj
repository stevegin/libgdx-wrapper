(ns stevegin.libgdx-wrapper.core
  (:require [clojure.core.async :as c])
  (:import (com.badlogic.gdx ApplicationListener Gdx)
           (com.badlogic.gdx.backends.lwjgl LwjglApplication)
           (com.badlogic.gdx.graphics GL10)))

(defprotocol PGameView
  (run-in-context [this funct] "funct must be a function of 0 arguments and
                                will be executed in the libgdx context."))

(defrecord GameView [state app root]
  PGameView
  (run-in-context [_ funct] nil))

(defn create-gameview
  "Creates a libgdx application.

   Every screen refresh render-fn is called with the current value of state-atom.

   Anything you want to do that calls libgdx api's will probably have to be done
   in render-fn because that's called in the right context. Alternatively if you
   have some code that you want to be executed just once in the opengl context
   and you are outside of render-fn you can send a function of 0 arguments to
   the gameview and it will be executed.
   (run-in-context my-fn) TODO: soon

   init-fn is called once at the start of the game in the libgdx context. It
   takes the current gamestate and should return a new gamestate (similar to
   calling swap! with it on the atom). This is to set up things like meshes that
   need to be created once in the gl context and saved to the gamestate.

   TODO:
   The gameview has some core.async channels on it that represent streams of
   user input so you can react to user input and update your state atom outside
   of render-fn. Ideally the render-fn will just be used for making opengl calls
   to draw things to the screen."
  [state-atom
   init-fn
   render-fn
   name
   screen-width
   screen-height]
  (let [app
        (proxy [ApplicationListener] []
          (resize [w h] nil)
          (create []
            (swap! state-atom init-fn))
          (render []
            (try (render-fn @state-atom)
                 (catch Exception e
                   ;; Clear Screen
                   (.glClear Gdx/gl GL10/GL_COLOR_BUFFER_BIT))))
          (pause [] nil)
          (dispose [] nil))
        root (LwjglApplication. app name screen-width screen-height false)]
    (GameView. state-atom app root)))
