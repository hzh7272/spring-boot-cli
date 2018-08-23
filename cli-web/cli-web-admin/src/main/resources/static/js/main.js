var cliComm = {
    http: {
        request: function (type) {
            if ('json' == type) {
                return axios.create({
                    timeout: 10000,
                    headers: {'Content-Type': 'application/json; charset=UTF-8'}
                });
            } else {
                return axios.create({
                    timeout: 10000,
                    transformRequest: [function (data) {
                        // 将数据转换为表单数据
                        var ret = ''
                        for (var it in data) {
                            ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                        }
                        return ret
                    }],
                    headers: {'Content-Type': 'application/json; charset=UTF-8'}
                });
            }
        },
        get: function(vue, option) {
            cliComm.http.request('form')
                .get(option.url, {
                    params: option.params
                }).then(function (res) {
                    cliComm.spin.hide(vue);
                    option.success(res.data);
                }).catch(function (ret) {
                    console.log(ret);
                    cliComm.spin.hide(vue, ret);
                    cliComm.http.getErrorNotice(vue, ret.response);
                });
        },
        post: function(vue, option) {
            vue.$Spin.show();
            cliComm.http.request('json')
                .post(option.url, option.params)
                .then(function (res) {
                    cliComm.spin.hide(vue);
                    option.success(res.data);
                }).catch(function (ret) {
                    console.log(ret);
                    cliComm.spin.hide(vue, ret);
                    cliComm.http.getErrorNotice(vue, ret.response);
                });
        },
        put: function(vue, option) {
            vue.$Spin.show();
            cliComm.http.request('json')
                .put(option.url, option.params)
                .then(function (res) {
                    cliComm.spin.hide(vue);
                    option.success(res.data);
                }).catch(function (ret) {
                    console.log(ret);
                    cliComm.spin.hide(vue, ret);
                    cliComm.http.getErrorNotice(vue, ret.response);
                });
        },
        delete: function(vue, option) {
            cliComm.http.request('form')
                .delete(option.url, {
                    params: option.params
                }).then(function (res) {
                cliComm.spin.hide(vue);
                option.success(res.data);
            }).catch(function (ret) {
                console.log(ret);
                cliComm.spin.hide(vue, ret);
                cliComm.http.getErrorNotice(vue, ret.response);
            });
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
            cliComm.http.get(vue, {
                url: url,
                params: vue.list.query,
                success: function (res) {
                    vue.list.data = res.data;
                    var index = (vue.list.query.page - 1) * 10 + 1;
                    for (var i in vue.list.data) {
                        vue.list.data[i].index = index++;
                    }
                }
            });
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
        update: function (vue, url, data) {
            cliComm.http.put(vue, {
                url: url,
                params: data,
                success: function (res) {
                    vue.back();
                    cliComm.notice.success(vue, '操作成功', '保存数据成功');
                }
            });
        },
        delete: function(vue, url) {
            cliComm.http.delete(vue, {
                url: url,
                success: function (res) {
                    vue.back();
                    cliComm.notice.success(vue, '操作成功', '删除数据成功');
                }
            });
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