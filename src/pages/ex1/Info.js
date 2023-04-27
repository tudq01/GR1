import React, { useEffect, useState } from "react";

import { Table, Form, Input, Select, Button } from "antd";

export const formItemLayout = {
  labelCol: {
    xs: { span: 24 },
    sm: { span: 4 },
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 18 },
  },
};
export const tailLayout = {
  wrapperCol: { offset: 4, span: 16 },
};
function Info() {
  const [data, setData] = useState([]);
  const [form] = Form.useForm();
  const handleDelete = (id) => {
    const newData = data.filter((e) => e.id !== id);
    let key = 1;
    newData.forEach((e) => {
      e.key = key;
      key++;
    });
    setData([...newData]);
  };
  const columns = [
    {
      title: "ID",
      dataIndex: "key",
    },
    {
      title: "MSSV",
      dataIndex: "studentId",
      render: (studentId) => <>{studentId}</>,
    },
    {
      title: "Name",
      dataIndex: "name",
      render: (name) => <>{name}</>,
    },
    {
      title: "DOB",
      dataIndex: "birth",
      render: (birth) => <>{birth}</>,
    },
    {
      title: "Email",
      dataIndex: "email",
      render: (email) => <>{email}</>,
    },
    {
      title: "Action",
      dataIndex: "id",
      render: (id) => {
        return (
          <>
            <Button type="primary" danger onClick={() => handleDelete(id)}>
              {"Xoa"}
            </Button>
          </>
        );
      },
    },
  ];
  const onFinish = async (values) => {
    const inputs = values;
    inputs.id = data.length + 1;
    inputs.key = data.length + 1;
    data.push(inputs);
    form.resetFields();
    setData([...data]);
    console.log(inputs);
  };
  useEffect(() => {
    let key = 1;
    test.forEach((data) => {
      data.key = key++;
    });
    setData(test);
  }, []);
  return (
    <div className="w-full  flex justify-center min-h-screen">
      <div className="flex flex-col gap-1 mt-10 shadow-lg p-8 h-3/4">
        <h2 className="text-2xl text-center font-bold">Table</h2>
        <div className="flex flex-col justify-center gap-5 mt-8">
          <div className="insert-form ">
            <Form
              form={form}
              labelAlign="left"
              {...formItemLayout}
              name="form_insert"
              onFinish={onFinish}
            >
              <Form.Item
                name="studentId"
                label={"MSSV"}
                rules={[
                  {
                    required: true,
                    message: "Nhập mssv!",
                  },
                ]}
              >
                <Input placeholder={"MSSV"} />
              </Form.Item>
              <Form.Item
                name="name"
                label={"Họ và tên"}
                rules={[
                  {
                    required: true,
                    message: "Nhập họ và tên!",
                  },
                ]}
              >
                <Input placeholder={"Họ và tên"} />
              </Form.Item>
              <Form.Item
                name="birth"
                label={"Ngày sinh"}
                rules={[
                  {
                    required: true,
                    message: "Nhập ngày sinh",
                  },
                ]}
              >
                <Input placeholder={"Ngày sinh"} />
              </Form.Item>
              <Form.Item
                name="email"
                label={"Email"}
                rules={[
                  {
                    required: true,
                    message: "Nhập email",
                  },
                ]}
              >
                <Input placeholder={"Email"} />
              </Form.Item>
              <Form.Item {...tailLayout}>
                <button
                  type="submit"
                  className="text-white bg-[#3b5998] hover:bg-[#3b5998]/90 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-3.5 py-1.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                >
                  Them{" "}
                </button>
              </Form.Item>
            </Form>
          </div>
          <Table columns={columns} dataSource={data} />
        </div>
      </div>
    </div>
  );
}

export default Info;

const test = [
  {
    id: 1,
    studentId: "20194812",
    name: "Dang Quoc Tu",
    birth: "13/12/2002",
    email: "vu.dq@gmail.com",
  },
  {
    id: 2,
    studentId: "20194812",
    name: "Mai Xuan Hai",
    birth: "13/2/2002",
    email: "hai.dq@gmail.com",
  },
  {
    id: 3,
    studentId: "20194812",
    name: "Vu Quang Trung",
    birth: "13/1/2002",
    email: "xuan.dq@gmail.com",
  },
  {
    id: 4,
    studentId: "20194812",
    name: "Nguyen Tien Tuan",
    birth: "13/7/2002",
    email: "nam.dq@gmail.com",
  },
  {
    id: 5,
    studentId: "20194812",
    name: "Dao Nam Trung",
    birth: "13/6/2002",
    email: "tuan.dq@gmail.com",
  },
];
