# subtitle-editor

Lightweight and easy to use subtitles editor library (only supports .srt for now):

```java
        // subtitle file
        String filename = "./input.srt";

        // get the file subtitles
        Subtitles subtitles = Subtitles.fromFile(filename);

        // add some time to all subtitles
        subtitles.add("00:02:03,456");

        // or subtract...
        subtitles.subtract("00:01:02,345");

        // or set relative speed, which will change every subtitle time by a factor,
        // so you can slow them down...
        subtitles.setRelativeSpeed(1.1)
        // or rush them a bit
        subtitles.setRelativeSpeed(0.9)

        // save file
        subtitles.toFile(filename);
```


