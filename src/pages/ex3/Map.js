import React from "react";
import Board from "../../components/board/Board";

function Map() {
  return (
    <div className="w-full  flex justify-center min-h-screen">
      <div className="flex flex-col gap-1 mt-10  p-8  w-3/4">
        <h2 className="text-2xl text-center font-bold">Draw Map</h2>

        <Board />
      </div>
    </div>
  );
}

export default Map;
