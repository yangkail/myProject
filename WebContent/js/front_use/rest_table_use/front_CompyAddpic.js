        $("#rs_view1").on('change', function () {
            $(".view_pic_display_img1").attr("src", "");
            var reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.addEventListener("load", function (e) {
                let base64 = e.target.result;
                $(".view_pic_display_img1").attr("src", base64);
            })
        })
        $("#rs_view2").on('change', function () {
            $(".view_pic_display_img2").attr("src", "");
            var reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.addEventListener("load", function (e) {
                let base64 = e.target.result;
                $(".view_pic_display_img2").attr("src", base64);
            })
        })
        $("#rs_view3").on('change', function () {
            $(".view_pic_display_img3").attr("src", "");
            var reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.addEventListener("load", function (e) {
                let base64 = e.target.result;
                $(".view_pic_display_img3").attr("src", base64);
            })
        })
        $("#rs_view4").on('change', function () {
            $(".view_pic_display_img4").attr("src", "");
            var reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.addEventListener("load", function (e) {
                let base64 = e.target.result;
                $(".view_pic_display_img4").attr("src", base64);
            })
        })
        $("#rs_view5").on('change', function () {
            $(".view_pic_display_img5").attr("src", "");
            var reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.addEventListener("load", function (e) {
                let base64 = e.target.result;
                $(".view_pic_display_img5").attr("src", base64);
            })
        })
        $("#rs_pic").on('change', function () {
            $(".pic_display_img").attr("src", "");
            var reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.addEventListener("load", function (e) {
                let base64 = e.target.result;
                $(".pic_display_img").attr("src", base64);
            })
        })