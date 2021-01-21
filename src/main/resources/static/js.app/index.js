let main = {
    init: function () {
        const _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        })
    },
    save: function () {
        let data = {
            title: $("#title").val(),
            author: $("#author").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: 'POST',
            url: '/data/board',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();