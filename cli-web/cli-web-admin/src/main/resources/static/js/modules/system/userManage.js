var vue = new Vue({
    el: '#cli',
    data: {
        list: {
            control: {
                show: true
            },
            column: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },
                {
                    title: '序号',
                    key: 'name'
                },
                {
                    title: '账号',
                    key: 'age'
                },
                {
                    title: '用户名',
                    key: 'age'
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
            data: [
                {
                    name: 'hong',
                    age: 12,
                    address: '2018-09-09 12:12:12'
                },
                {
                    name: 'hong',
                    age: 12,
                    address: '2018-09-09 12:12:12'
                },
                {
                    name: 'hong',
                    age: 12,
                    address: '2018-09-09 12:12:12'
                },
                {
                    name: 'hong',
                    age: 12,
                    address: '2018-09-09 12:12:12'
                },
                {
                    name: 'hong',
                    age: 12,
                    address: '2018-09-09 12:12:12'
                },
                {
                    name: 'hong',
                    age: 12,
                    address: '2018-09-09 12:12:12'
                },
                {
                    name: 'hong',
                    age: 12,
                    address: '2018-09-09 12:12:12'
                },
                {
                    name: 'hong',
                    age: 12,
                    address: '2018-09-09 12:12:12'
                },
                {
                    name: 'hong',
                    age: 12,
                    address: '2018-09-09 12:12:12'
                },
                {
                    name: 'hong',
                    age: 12,
                    address: '2018-09-09 12:12:12'
                }
            ]
        },
        edit: {
            control: {
                show: false,
                editFlag: false,
                title: '新增'
            }
        }
    },
    methods: {
        search: function () {
            
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
        }
    }
});