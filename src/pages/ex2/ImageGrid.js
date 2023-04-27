import React, { useEffect, useState } from "react";

const url = " https://api.pipgo.vn/images/avatar/1677919330146-745299311.jpeg";
function ImageGrid() {
  const [images, setImage] = useState([]);
  const [count, setCount] = useState("");
  const handleChange = (event) => {
    const inputValue = event.target.value;
    console.log(inputValue);
    setCount(inputValue);
  };
  useEffect(() => {
    const numOfImg = Number(count);
    const imgs = [];
    for (let i = 0; i < numOfImg; i++) {
      imgs.push({ id: i, url: url });
    }
    setImage([...imgs]);
  }, [count]);
  return (
    <div className="w-full  flex justify-center min-h-screen">
      <div className="flex flex-col gap-1 mt-10  p-8 h-3/4 w-3/4">
        <h2 className="text-2xl text-center font-bold">Image Grid</h2>
        <div className="flex flex-col  gap-5 mt-8 ">
          <div className="form-input flex flex-row gap-4">
            <label htmlFor="number-input" className="text-lg font-bold mb-2">
              Enter a number:
            </label>
            <input
              type="number"
              id="number-input"
              className="px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
              value={count}
              onChange={handleChange}
            />
          </div>
          <div className="img grid grid-cols-3 gap-3">
            {images.map((image) => (
              <div key={image.id} className="">
                <img
                  src={image.url}
                  alt=""
                  className="rounded object-cover w-full h-full"
                />
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default ImageGrid;
