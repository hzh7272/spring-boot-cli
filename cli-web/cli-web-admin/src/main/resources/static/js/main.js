var cliComm = {
    http: {
        get: function(vue, option) {

        },
        post: function(vue, option) {

        },
        put: function(vue, option) {

        },
        delete: function(vue, option) {

        }
    },
    func: {
        reset: function(vue, url) {

        },
        query: function(vue, url) {

        },
        info: function(vue, url) {

        },
        save: function(vue, url, data) {

        },
        delete: function(vue, url) {

        },
        deleteBatch: function(vue, url, ids) {

        }
    },
    notice: {
        success: function(vue, title, msg) {
            vue.$Notice.config({
                top: 50
            });
            vue.$Notice.success({
                title: title,
                desc: msg,
                duration: 3
            });
        },
        info: function(vue, title, msg) {
            vue.$Notice.config({
                top: 50
            });
            vue.$Notice.info({
                title: title,
                desc: msg,
                duration: 3
            });
        },
        warning: function(vue, title, msg) {
            vue.$Notice.config({
                top: 50
            });
            vue.$Notice.warning({
                title: title,
                desc: msg,
                duration: 3
            });
        },
        error: function(vue, title, msg) {
            vue.$Notice.config({
                top: 50
            });
            vue.$Notice.error({
                title: title,
                desc: msg,
                duration: 3
            });
        }
    }
};