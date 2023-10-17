package com.example.lagaltcaseapplication.enums;

public enum Skills {
    // WEB DEVELOPMENT
    JAVA(1, "Java"),
    PYTHON(2, "Python"),
    JAVASCRIPT(3, "JavaScript"),
    HTML(4, "HTML"),
    CSS(5, "CSS"),
    REACT(6, "React"),
    NODEJS(7, "Node.js"),
    ANGULAR(8, "Angular"),
    VUEJS(9, "Vue.js"),
    BOOTSTRAP(10, "Bootstrap"),
    DJANGO(11, "Django"),
    RUBYONRAILS(12, "Ruby on Rails"),
    ASPNET(13, "ASP.NET"),
    PHP(14, "PHP"),
    LARAVEL(15, "Laravel"),
    // GRAPHIC DESIGN
    PHOTOSHOP(16, "Adobe Photoshop"),
    ILLUSTRATOR(17, "Adobe Illustrator"),
    INDESIGN(18, "Adobe InDesign"),
    XD(19, "Adobe XD"),
    SKETCH(20, "Sketch"),
    CORELDRAW(21, "CorelDraw"),
    AFFINITYDESIGNER(22, "Affinity Designer"),
    GIMP(23, "GIMP"),
    CANVA(24, "Canva"),
    PAINTTOOLSAI(25, "PaintTool SAI"),
    LIGHTROOM(26, "Adobe Lightroom"),
    AUTODESKSKETCHBOOK(27, "Autodesk Sketchbook"),
    PROCREATE(28, "Procreate"),
    QUARKXPRESS(29, "QuarkXPress"),
    INKSCAPE(30, "Inkscape"),
    // GAME DEVELOPMENT
    UNITY(31, "Unity"),
    UNREALENGINE(32, "Unreal Engine"),
    GODOT(33, "Godot"),
    BLENDER(34, "Blender"),
    MAYA(35, "Maya"),
    CRYENGINE(36, "CryEngine"),
    GAMEMAKER(37, "Game Maker Studio"),
    RPGMAKER(38, "RPG Maker"),
    CONSTRUCT(39, "Construct"),
    PHASER(40, "Phaser"),
    MONOGAME(41, "MonoGame"),
    AMAZONLUMBERYARD(42, "Amazon Lumberyard"),
    ARMORY(43, "Armory"),
    STENCYL(44, "Stencyl"),
    PICO8(45, "Pico-8"),
    // MUSIC
    FLSTUDIO(46, "FL Studio"),
    ABLETON(47, "Ableton Live"),
    LOGICPROX(48, "Logic Pro X"),
    GARAGEBAND(49, "GarageBand"),
    PROTOOLS(50, "Pro Tools"),
    CUBASE(51, "Cubase"),
    REAPER(52, "Reaper"),
    REASON(53, "Reason"),
    BITWIGSTUDIO(54, "Bitwig Studio"),
    AUDACITY(55, "Audacity"),
    SIBELIUS(56, "Sibelius"),
    FRUITYLOOPS(57, "Fruity Loops"),
    NUENDO(58, "Nuendo"),
    STUDIOONE(59, "Studio One"),
    MASCHINE(60, "Maschine"),
    // FILM
    RESOLVE(61, "DaVinci Resolve"),
    PREMIERE(62, "Adobe Premiere Pro"),
    FINALCUTPRO(63, "Final Cut Pro"),
    AFTEREFFECTS(64, "Adobe After Effects"),
    SONYVEGAS(65, "Vegas Pro"),
    AVID(66, "Avid Media Composer"),
    LIGHTWORKS(67, "Lightworks"),
    CAMTASIA(68, "Camtasia"),
    FILMORA(69, "Filmora"),
    HITFILM(70, "HitFilm"),
    PINNACLESTUDIO(71, "Pinnacle Studio"),
    POWERDIRECTOR(72, "PowerDirector"),
    IMOVIE(73, "iMovie"),
    VIDEOPAD(74, "VideoPad"),
    KINEMASTER(75, "KineMaster");

    private final int id;
    private final String name;

    Skills(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Skills getById(int id) {
        for(Skills e : values()) {
            if(e.id == id) return e;
        }
        throw new IllegalArgumentException("Invalid Skills ID");
    }


}

