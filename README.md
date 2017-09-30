# subtitle-editor

Lightweight and easy to use subtitles editor (only supports .srt for now):

```java
        // subtitle file
        String filename = "./input.srt";

        // get the file subtitles
        Subtitles subtitles = Subtitles.fromFile(filename);

        // add some time to all subtitles
        subtitles.add("00:02:03,456");

        // or subtract...
        subtitles.subtract("00:01:02,345");

        // save file
        subtitles.toFile(filename);
```


