# subtitle-editor

Lightweight and easy to use subtitles editor (only supports .srt for now):

```java
        // subtitle file
        String filename = "./input.srt";

        // get the file subtitles
        Subtitles subtitles = Subtitles.fromFile(filename);

        // add some time to all subtitles
        subtitles.add("00:02:03,456");

        // save file
        subtitles.toFile(filename);
```


