import './App.css';
import {
  RouterProvider, createBrowserRouter } from 'react-router-dom';
import ListUsers from "./components/user/ListUsersComponent";
import RequestStatusComponent from './components/RequestStatusComponent';
import PaymentComponent from './components/PaymentComponent';
import CreditTypeComponent from './components/CreditTypeComponent';
import CreditRequestComponent from './components/CreditRequestComponent';
import CreditComponent from './components/CreditComponent';
import React from "react";
import Root from "./components/RootComponent";
import Roles from "./components/role/RoleComponent";
import AddUser from "./components/user/AddUserComponent";
import UpdateUser, { loader } from "./components/user/UpdateUserComponent";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <App />,
    children: [
      {
        path: "/api/users",
        element: <ListUsers />
      },
      // {
      //   path: "/api/users/:id",
      //   element: <User />
      // },
      {
        path: "/api/users/update/:id",
        element: <UpdateUser />,
        loader: loader
      },
      {
        path: "/api/users/add",
        element: <AddUser />
      },
      {
        path: "/api/roles",
        element: <Roles />
      },
      {
        path: "/api/request_statuses",
        element: <RequestStatusComponent />
      },
      {
        path: "/api/payments",
        element: <PaymentComponent />
      },
      {
        path: "/api/credit_type",
        element: <CreditTypeComponent />
      },
      {
        path: "/api/credit_requests",
        element: <CreditRequestComponent />
      },
      {
        path: "/api/credits",
        element: <CreditComponent />
      }
    ]
  }
]);

export default function App() {
    return (
      <RouterProvider router={router}>
      </RouterProvider>
  );
}

