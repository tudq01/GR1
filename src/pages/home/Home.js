import React from "react";
import { Link } from "react-router-dom";
import "../../index.css";
function Home() {
  return (
    <div className="w-full  flex justify-center min-h-screen">
      <div className="flex flex-col gap-1 mt-10">
        <h2 className="text-2xl text-center font-bold">Index</h2>
        <div className="flex flex-col justify-center gap-5 mt-8">
          <Link
            to={"/ex1"}
            className=" text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
          >
            Exercise 1
          </Link>
          <Link
            to={"/ex2"}
            className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
          >
            {" "}
            Excercise 2
          </Link>
          <Link
            to={"/ex3"}
            className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
          >
            Exercise 3
          </Link>
          <Link
            to={"/ex4"}
            className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
          >
            Exercise 4
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Home;
