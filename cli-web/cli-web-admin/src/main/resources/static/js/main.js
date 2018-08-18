var cliComm = {
    http: {
        request: function () {
            return axios.create({
                timeout: 1000,
                transformRequest: [function (data) {
                    // 将数据转换为表单数据
                    var ret = ''
                    for (var it in data) {
                        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                    }
                    return ret
                }],
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            });
        },
        get: function(vue, option) {

        },
        post: function(vue, option) {
            vue.$Spin.show();
            cliComm.http.request()
                .post(option.url, option.params)
                .then(function (res) {
                    cliComm.spin.hide(vue);
                    option.success(res);
                })
                .catch(function (ret) {
                    console.log(ret);
                    cliComm.spin.hide(vue, ret);
                    cliComm.http.getErrorNotice(vue, ret.response);
                });
        },
        put: function(vue, option) {

        },
        delete: function(vue, option) {

        },
        getErrorNotice: function (vue, response) {
            var title = "";
            if (400 == response.status) {
                title = "参数有误";
            }

            cliComm.notice.error(vue, title, response.data);
        }
    },
    spin: {
        show: function(vue) {
            vue.$Spin.show();
        },
        hide: function(vue, ret) {
            vue.$Spin.hide();
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
            cliComm.http.post(vue, {
                url: url,
                params: data,
                success: function (res) {
                    vue.back();
                    cliComm.notice.success(vue, '操作成功', '保存数据成功');
                }
            });
        },
        delete: function(vue, url) {

        },
        deleteBatch: function(vue, url, ids) {

        }
    },
    notice: {
        success: function(vue, title, msg) {
            vue.$Notice.success({
                title: title,
                desc: msg,
                duration: 3
            });
        },
        info: function(vue, title, msg) {
            // vue.$Notice.config({
            //     top: 50
            // });
            vue.$Notice.info({
                title: title,
                desc: msg,
                duration: 3
            });
        },
        warning: function(vue, title, msg) {
            // vue.$Notice.config({
            //     top: 50
            // });
            vue.$Notice.warning({
                title: title,
                desc: msg,
                duration: 3
            });
        },
        error: function(vue, title, msg) {
            // vue.$Notice.config({
            //     top: 50
            // });
            vue.$Notice.error({
                title: title,
                desc: msg,
                duration: 3
            });
        }
    }
};