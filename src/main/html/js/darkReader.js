DarkReader.setFetchMethod(window.fetch);
// Enable when the system color scheme is dark.
function switchColor() {
    const isEnabled = DarkReader.isEnabled();

    if (isEnabled) {
        DarkReader.disable();
    } else {
        DarkReader.setFetchMethod(window.fetch);
        DarkReader.enable({
            brightness: 100,
            contrast: 90,
            sepia: 10
        });
        // Enable when the system color scheme is dark.
        DarkReader.auto({
            brightness: 100,
            contrast: 90,
            sepia: 10
        });
    }
};