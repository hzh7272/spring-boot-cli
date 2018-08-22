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
                    if ('' == vue.list.info.id) {
                        cliComm.http.post(vue, {
                            url: url,
                            params: vue.list.info,
                            success: function (res) {
                                cliComm.notice.success(vue, '操作成功', '保存数据成功');
                                vue.loadAuthTree();
                            }
                        });
                    } else {
                        cliComm.http.put(vue, {
                            url: url,
                            params: vue.list.info,
                            success: function (res) {
                                cliComm.notice.success(vue, '操作成功', '保存数据成功');
                                vue.loadAuthTree();
                            }
                        });
                    }
                }
            });
        }
        // search: function () {
        //     cliComm.func.query(vue, path + 'system/users');
        // },
        // add: function () {
        //     vue.list.control.show = false;
        //     vue.edit.control.show = true;
        //     vue.edit.control.editFlag = false;
        //     vue.edit.control.title = '新增';
        // },
        // edit: function (data) {
        //     vue.list.control.show = false;
        //     vue.edit.control.show = true;
        //     vue.edit.control.editFlag = true;
        //     vue.edit.control.title = '编辑';
        // },
        // delete: function (data) {
        //
        // },
        // deleteBatch: function (data) {
        //
        // },
        // back: function () {
        //     vue.list.control.show = true;
        //     vue.edit.control.show = false;
        //     vue.edit.control.editFlag = false;
        //     vue.edit.control.title = '';
        // },
        // clickUpload: function () {
        //     document.getElementById('avatarUrlUpload').firstChild.firstChild.click();
        // },
        // save: function () {
        //     vue.$refs.editForm.validate(function (valid) {
        //         if (valid) {
        //             var url = path + 'system/user';
        //             cliComm.func.save(vue, url, vue.edit.form);
        //         }
        //     });
        // }
    }
});

vue.loadAuthTree();