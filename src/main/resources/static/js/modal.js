$(document).ready(function() {
    $(".open-modal").on("click", function() {
        let name = $(this).data("name");
        let description = $(this).data("description");
        $("#modal-title").text(name);
        $("#modal-description").text(description);
        $("#modal").show();
    });

    $(".close").on("click", function() {
        $("#modal").hide();
    });

    $(window).on("click", function(event) {
        if ($(event.target).is("#modal")) {
            $("#modal").hide();
        }
    });
});
