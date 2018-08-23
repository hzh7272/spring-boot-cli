var vue = new Vue({
    el: '#cli',
    data: {
        list: {
            control: {
                show: true,
                showInfo: false,
                disabled: false,
                authParentId: ''
            },
            authTree: [],
            info: {
                id: '',
                parentId: '',
                species: '',
                type: 1,
                name: '',
                resource: '',
                icon: '',
                permission: '',
                sort: 1,
                state: 1
            },
            rules: {
                name: [
                    {required: true, message: '请输入权限名称', trigger: 'blur'}
                ],
                permission: [
                    {required: true, message: '请输入权限表达式', trigger: 'blur'}
                ],
                resource: [
                    {required: true, validator: function (rule, value, callback) {
                            if (1 === vue.list.info.type && value === '') {
                                callback(new Error('请输入资源路径'));
                            } else {
                                callback();
                            }
                        }, trigger: 'blur'}
                ],
                icon: [
                    {required: true, validator: function (rule, value, callback) {
                            if (1 === vue.list.info.type && value === '') {
                                callback(new Error('请输入图标icon'));
                            } else {
                                callback();
                            }
                        }, trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        loadAuthTree: function () {
            cliComm.http.get(vue, {
                url: path + "auth/tree",
                success: function (res) {
                    vue.list.authTree = res;
                    vue.info(vue.list.authTree[0].id);
                }
            });
        },
        info: function (id) {
            cliComm.http.get(vue, {
                url: path + "auth/" + id,
                success: function (res) {
                    vue.list.info = res;
                }
            });
        },
        changeAuthSpecies: function (species) {
            if ('system' != species) {
                vue.list.info.type = 1;
                vue.list.control.disabled = true;
            } else {
                vue.list.control.disabled = false;
            }
        },
        selectTreeItem: function(items) {
            if (0 < items.length) {
                vue.list.control.showInfo = true;
                vue.list.control.authParentId = items[0].id;
                vue.info(vue.list.control.authParentId);
            } else {
                vue.list.control.authParentId = '';
                vue.list.control.showInfo = false;
            }
        },
        addTopAuth: function() {
            vue.list.control.showInfo = true;
            vue.$refs.authForm.resetFields();
            vue.list.info.id = '';
            vue.list.info.species = 'system';
        },
        addChildrenAuth: function() {
            if ('' == vue.list.control.authParentId) {
                cliComm.notice.warning(vue, '操作错误', '请选择父级权限')
            } else {
                vue.$refs.authForm.resetFields();
                vue.list.info.id = '';
                vue.list.info.parentId = vue.list.control.authParentId;
                vue.list.info.species = 'system';
            }
        },
        save: function () {
            vue.$refs.authForm.validate(function (valid) {
                if (valid) {
                    var url = path + "auth";
                    var option = {
                        url: url,
                        params: vue.list.info,
                        success: function (res) {
                            cliComm.notice.success(vue, '操作成功', '保存数据成功');
                            vue.loadAuthTree();
                        }
                    };
                    if ('' == vue.list.info.id) {
                        cliComm.http.post(vue, option);
                    } else {
                        cliComm.http.put(vue, option);
                    }
                }
            });
        },
        deleteAuth: function () {
            if ('' != vue.list.info.id) {
                cliComm.http.get(vue, {
                    url: path + 'auth/' + vue.list.info.id + '/check',
                    success: function (res) {
                        if (0 < res) {
                            cliComm.notice.warning(vue, '操作错误', '请先删除子节点');
                        } else {
                            cliComm.http.delete(vue, {
                                url: path + 'auth/' + vue.list.info.id,
                                success: function () {
                                    cliComm.notice.success(vue, '操作成功', '删除数据成功');
                                    vue.loadAuthTree();
                                }
                            });
                        }
                    }
                });
            }
        }
    }
});

vue.loadAuthTree();