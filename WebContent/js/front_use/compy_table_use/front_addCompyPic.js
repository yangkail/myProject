$("#cp_business").on('change',function() {
			$(".pic_img").remove();
			var reader = new FileReader();
			reader.readAsDataURL(this.files[0])
			reader.addEventListener("load", function(e) {
				console.log(reader);
				var base64 = e.target.result;
				$(".cp_business_diaplay").append(
						'<img src="' + base64 + '" class="pic_img">')
			})
		})
$("#cp_logo").on('change',function() {
			$("div.cp_logo_display img.pic_img_cycle").remove();
			var reader = new FileReader();
			reader.readAsDataURL(this.files[0])
			reader.addEventListener("load", function(e) {
				console.log(reader);
				var base64 = e.target.result;
				$(".cp_logo_display").append(
						'<img src="' + base64 + '" class="pic_img_cycle">')
			})
		})
$("#cp_bigpic").on('change',function() {
			$("div.cp_bigpic_display img.pic_img_cycle").remove();
			var reader = new FileReader();
			reader.readAsDataURL(this.files[0])
			reader.addEventListener("load", function(e) {
				console.log(reader);
				var base64 = e.target.result;
				$(".cp_bigpic_display").append(
						'<img src="' + base64 + '" class="pic_img_cycle">')
			})
		})