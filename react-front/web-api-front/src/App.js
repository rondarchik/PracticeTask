import './App.css';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import RequestStatusComponent from './components/RequestStatusComponent';
import PaymentComponent from './components/PaymentComponent';
import CreditTypeComponent from './components/CreditTypeComponent';
import CreditRequestComponent from './components/CreditRequestComponent';
import CreditComponent from './components/CreditComponent';
import React from "react";
import Root from "./components/RootComponent";
import ListUsers from "./components/user/ListUsersComponent";
import AddUser from "./components/user/AddUserComponent";
import UpdateUser, { loader as UserLoader } from "./components/user/UpdateUserComponent";
import ListRoles from "./components/role/ListRoleComponent";
import AddRole from "./components/role/AddRoleComponent";
import UpdateRole, { loader as RoleLoader } from "./components/role/UpdateRoleComponent";

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
      {
        path: "/api/users/update/:id",
        element: <UpdateUser />,
        loader: UserLoader
      },
      {
        path: "/api/users/add",
        element: <AddUser />
      },
      {
        path: "/api/roles",
        element: <ListRoles />
      },
      // {
      //   path: "/api/roles/add",
      //   element: <AddRole />
      // },
      {
        path: "/api/roles/update/:id",
        element: <UpdateRole />,
        loader: RoleLoader
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

