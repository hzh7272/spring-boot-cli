var vue = new Vue({
    el: '#cli',
    data: {
        list: {
            control: {
                show: true,
                index: 1
            },
            query: {
                page: 1,
                keyword: '66',
                roleId: ''
            },
            column: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },
                {
                    title: '序号',
                    key: 'account'
                },
                {
                    title: '账号',
                    key: 'account'
                },
                {
                    title: '用户名',
                    key: 'nickName'
                },
                {
                    title: '角色',
                    key: 'age'
                },
                {
                    title: '状态',
                    key: 'age'
                },
                {
                    title: '创建时间',
                    key: 'age'
                },
                {
                    title: '修改时间',
                    key: 'address'
                },
                {
                    title: '修改人',
                    key: 'age'
                },
                {
                    title: '操作',
                    render: function (h, params) {
                        return h('div', [
                            h('i-button', {
                                props: {
                                    type: 'info',
                                    icon: 'md-create',
                                    size: 'small'
                                }
                            }, '编辑')
                        ]);
                    }
                }
            ],
            data: []
        },
        edit: {
            control: {
                show: false,
                editFlag: false,
                title: '新增'
            },
            form: {
                id: '',
                avatarUrl: '',
                account: '',
                nickName: '',
                roleId: '',
                password: '',
                state: 1
            },
            rules: {
                account: [
                    {required: true, message: "请输入用户账号", trigger: "blur"}
                ],
                nickName: [
                    {required: true, message: "请输入用户昵称", trigger: "blur"}
                ],
                roleId: [
                    {required: true, message: "请选中用户角色", trigger: "change"}
                ],
                password: [
                    {required: true, message: "请输入用户初始密码", trigger: "blur"}
                ]
            }
        }
    },
    methods: {
        search: function () {
            cliComm.func.query(vue, path + 'system/users');
        },
        add: function () {
            vue.list.control.show = false;
            vue.edit.control.show = true;
            vue.edit.control.editFlag = false;
            vue.edit.control.title = '新增';
        },
        edit: function (data) {
            vue.list.control.show = false;
            vue.edit.control.show = true;
            vue.edit.control.editFlag = true;
            vue.edit.control.title = '编辑';
        },
        delete: function (data) {

        },
        deleteBatch: function (data) {

        },
        back: function () {
            vue.list.control.show = true;
            vue.edit.control.show = false;
            vue.edit.control.editFlag = false;
            vue.edit.control.title = '';
        },
        clickUpload: function () {
            document.getElementById('avatarUrlUpload').firstChild.firstChild.click();
        },
        save: function () {
            vue.$refs.editForm.validate(function (valid) {
                if (valid) {
                    var url = path + 'system/user';
                    cliComm.func.save(vue, url, vue.edit.form);
                }
            });
        }
    }
});

vue.search();