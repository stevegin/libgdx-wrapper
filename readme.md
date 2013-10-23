Note: This is way super pre alpha broken incomplete.

# Stevegin

Stevegin is just a collection of libraries I'm putting together to make games in clojure. I am trying to do things in a way that is different from most game "Engines" in that I don't want one big monolithic framework but instead a bunch of libraries that can be mixed and matched and work together.

# libgdx-wrapper

Libgdx wrapper is the first of these library. It creates a libgdx context for you and allows you to give it an atom that represents your gamestate, a render function for displaying that data in an opengl context and (soon) some core.async channels you can listen to for things like user input.

It dictates nothing about how your game updates, when your code runs or what your gamestate looks like. The way you update that atom and interpret it's data to draw to screen are entirely up to you.

I will probably have other libraries that manage things like an update loop for you that you could use but this library is simply to set up the opengl (libgdx) context and provide somew ways for you to interact with it.
